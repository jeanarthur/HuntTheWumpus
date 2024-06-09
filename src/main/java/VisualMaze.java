import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VisualMaze {

    Map<String, MazeElement> elements;
    private boolean TRACK_PLAYER_MOVEMENT;
    private boolean SHOW_CAVE_ENEMIES;

    private int fixX;
    private int fixY;

    public VisualMaze(Cave root) {
        this.elements = new HashMap<>();
        this.elements.put(root.toString(), new MazeElement("R", 0, 0, Color.GREEN));

        this.TRACK_PLAYER_MOVEMENT = true;
        this.SHOW_CAVE_ENEMIES = true;
    }

    public void createVisualConnection(Cave from, Cave to) {
        if (to == null){
            return;
        }

        if (!elements.containsKey(from.toString())) {
            throw new IllegalArgumentException("Parameter 'from' can be previous registered on caveElements");
        }

        MazeElement fromElement = elements.get(from.toString());

        if (from.getNorth() != null && from.getNorth().equals(to)){
            elements.put(from + to.toString(), new MazeElement("|", fromElement.x, fromElement.y + 1));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x, fromElement.y + 2));
        }
        else if (from.getSouth() != null && from.getSouth().equals(to)) {
            elements.put(from + to.toString(), new MazeElement("|", fromElement.x, fromElement.y - 1));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x, fromElement.y - 2));
        }
        else if (from.getEast() != null && from.getEast().equals(to)) {
            elements.put(from + to.toString(), new MazeElement("-", fromElement.x + 1, fromElement.y));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x + 2, fromElement.y));
        }
        else if (from.getWest() != null && from.getWest().equals(to)) {
            elements.put(from + to.toString(), new MazeElement("-", fromElement.x - 1, fromElement.y));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x - 2, fromElement.y));
        }
        else {
            throw new IllegalArgumentException("Parameter 'to' not has connected to Cave provided in 'from' parameter");
        }
    }

    private MazeElement defineMazeElementToCave(Cave cave, int x, int y) {
        if (!this.SHOW_CAVE_ENEMIES || cave.getEnemy() == null){
            return new MazeElement("C", x, y);
        }

        Class enemyClass = cave.getEnemy().getClass();
        if (enemyClass.equals(Bat.class)) {
            return new MazeElement("B", x, y, Color.PURPLE);
        } else if (enemyClass.equals(Hole.class)){
            return new MazeElement("P", x, y, Color.BLUE);
        } else if (enemyClass.equals(Wumpus.class)) {
            return new MazeElement("W", x, y, Color.RED);
        } else if (enemyClass.equals(Arrow.class)){
            return new MazeElement("F", x, y, Color.GREEN);
        }

        return new MazeElement("E", x, y, Color.RED);
    }

    public void updateElementSymbol(Cave cave) {
        MazeElement currentElement = elements.get(cave.toString());
        MazeElement updatedElement = this.defineMazeElementToCave(cave, currentElement.x, currentElement.y);
        elements.put(cave.toString(), updatedElement);
    }

    public void updateElementSymbol(Cave cave, String symbol) {
        MazeElement mazeElement = elements.get(cave.toString());
        mazeElement.symbol = symbol;
        elements.put(cave.toString(), mazeElement);
    }

    public void updateElementSymbol(Cave cave, String symbol, Color color) {
        MazeElement mazeElement = elements.get(cave.toString());
        mazeElement.symbol = symbol;
        mazeElement.color = color;
        elements.put(cave.toString(), mazeElement);
    }

    public void updateElementSymbol(String elementId, String symbol, Color color){
        MazeElement mazeElement = elements.get(elementId);
        mazeElement.symbol = symbol;
        mazeElement.color = color;
        elements.put(elementId, mazeElement);
    }

    public void updateElementColor(String elementId, Color color){
        MazeElement mazeElement = elements.get(elementId);
        mazeElement.color = color;
        elements.put(elementId, mazeElement);
    }

    public void registerPlayerMovement(Cave from, Cave to) {
        this.updateElementSymbol(from);
        this.updateElementSymbol(to, "P", Color.YELLOW);

        if (this.TRACK_PLAYER_MOVEMENT){
            MazeElement mazeElement = elements.get(from + to.toString());
            if (mazeElement != null){
                updateElementColor(from + to.toString(), Color.CYAN);
            } else if (elements.get(to + from.toString()) != null) {
                updateElementColor(to + from.toString(), Color.CYAN);
            }

            if (!this.SHOW_CAVE_ENEMIES || elements.get(from.toString()).symbol.equals("C")){
                updateElementColor(from.toString(), Color.CYAN);
            }
        }
    }

    public void print() {
        int mapHeight = getHeight();
        int mapWidth = getWidth();
        String[][] map = new String[mapHeight][mapWidth];

        // System.out.printf("Dimensões: %d x %d\n", mapHeight, mapWidth);

        for (MazeElement mazeElement : elements.values()){
            map[Math.abs(mazeElement.y - fixY)][mazeElement.x + fixX] = (mazeElement.color != null ? mazeElement.color.value() : "") + mazeElement.symbol + Color.RESET.value();
        }

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (map[i][j] != null){
                    System.out.print(map[i][j]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private int getWidth() {
        int minX = 0;
        int maxX = 0;

        for (MazeElement mazeElement : elements.values()){
            minX = Math.min(minX, mazeElement.x);
            maxX = Math.max(maxX, mazeElement.x);
        }

        fixX = Math.abs(minX);
        return Math.abs(minX) + maxX + 1;
    }

    private int getHeight() {
        int minY = 0;
        int maxY = 0;

        for (MazeElement mazeElement : elements.values()){
            minY = Math.min(minY, mazeElement.y);
            maxY = Math.max(maxY, mazeElement.y);
        }

        fixY = maxY;
        return Math.abs(minY) + maxY + 1;
    }
}
