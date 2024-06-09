import java.util.HashMap;
import java.util.Map;

public class VisualMaze {

    Map<String, MazeElement> elements;
    private boolean TRACK_PLAYER_MOVEMENT;
    private boolean SHOW_ALL_CAVE_ENEMIES;
    private boolean SHOW_DISCOVERED_ENEMIES;
    private boolean SHOW_MAZE;
    private boolean SHOW_ONLY_DISCOVERED_CAVES;

    private int fixX;
    private int fixY;

    public VisualMaze(Cave root, GameModes gameMode) {
        this.elements = new HashMap<>();
        this.elements.put(root.toString(), new MazeElement("R", 0, 0, Color.GREEN, !this.SHOW_ONLY_DISCOVERED_CAVES));

        switch (gameMode){
            case FULL_MAP -> {
                this.TRACK_PLAYER_MOVEMENT = true;
                this.SHOW_ALL_CAVE_ENEMIES = false;
                this.SHOW_MAZE = true;
                this.SHOW_DISCOVERED_ENEMIES = true;
                this.SHOW_ONLY_DISCOVERED_CAVES = false;
            }
            case PATH_MAP -> {
                this.TRACK_PLAYER_MOVEMENT = true;
                this.SHOW_ALL_CAVE_ENEMIES = false;
                this.SHOW_MAZE = true;
                this.SHOW_DISCOVERED_ENEMIES = true;
                this.SHOW_ONLY_DISCOVERED_CAVES = true;
            }
            case WITHOUT_MAP -> {
                this.TRACK_PLAYER_MOVEMENT = false;
                this.SHOW_ALL_CAVE_ENEMIES = false;
                this.SHOW_MAZE = false;
                this.SHOW_DISCOVERED_ENEMIES = false;
                this.SHOW_ONLY_DISCOVERED_CAVES = false;
            }
            case WALL_HACK -> {
                this.TRACK_PLAYER_MOVEMENT = true;
                this.SHOW_ALL_CAVE_ENEMIES = true;
                this.SHOW_MAZE = true;
                this.SHOW_DISCOVERED_ENEMIES = true;
                this.SHOW_ONLY_DISCOVERED_CAVES = false;
            }
        }
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
            elements.put(from + to.toString(), new MazeElement("|", fromElement.x, fromElement.y + 1, !this.SHOW_ONLY_DISCOVERED_CAVES));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x, fromElement.y + 2));
        }
        else if (from.getSouth() != null && from.getSouth().equals(to)) {
            elements.put(from + to.toString(), new MazeElement("|", fromElement.x, fromElement.y - 1, !this.SHOW_ONLY_DISCOVERED_CAVES));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x, fromElement.y - 2));
        }
        else if (from.getEast() != null && from.getEast().equals(to)) {
            elements.put(from + to.toString(), new MazeElement("-", fromElement.x + 1, fromElement.y, !this.SHOW_ONLY_DISCOVERED_CAVES));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x + 2, fromElement.y));
        }
        else if (from.getWest() != null && from.getWest().equals(to)) {
            elements.put(from + to.toString(), new MazeElement("-", fromElement.x - 1, fromElement.y, !this.SHOW_ONLY_DISCOVERED_CAVES));
            elements.put(to.toString(), defineMazeElementToCave(to, fromElement.x - 2, fromElement.y));
        }
        else {
            throw new IllegalArgumentException("Parameter 'to' not has connected to Cave provided in 'from' parameter");
        }
    }

    private MazeElement defineMazeElementToCave(Cave cave, int x, int y) {
        MazeElement mazeElement = elements.get(cave.toString());
        boolean hasVisible = (mazeElement != null && mazeElement.visible) || !this.SHOW_ONLY_DISCOVERED_CAVES;

        if (!this.SHOW_DISCOVERED_ENEMIES || cave.getEnemy() == null){
            return new MazeElement("C", x, y, hasVisible);
        }

        boolean hasDiscovered = mazeElement != null && mazeElement.discovered;

        Class enemyClass = cave.getEnemy().getClass();
        String symbol = "C";
        Color color = Color.RESET;
        if (enemyClass.equals(Bat.class)) {
            symbol = (this.SHOW_ALL_CAVE_ENEMIES || (this.SHOW_DISCOVERED_ENEMIES && hasDiscovered)) ? "B" : symbol;
            color = (this.SHOW_ALL_CAVE_ENEMIES || (this.SHOW_DISCOVERED_ENEMIES && hasDiscovered)) ? Color.PURPLE : color;
        } else if (enemyClass.equals(Hole.class)){
            symbol = (this.SHOW_ALL_CAVE_ENEMIES || (this.SHOW_DISCOVERED_ENEMIES && hasDiscovered)) ? "P" : symbol;
            color = (this.SHOW_ALL_CAVE_ENEMIES || (this.SHOW_DISCOVERED_ENEMIES && hasDiscovered)) ? Color.BLUE : color;
        } else if (enemyClass.equals(Wumpus.class)) {
            symbol = (this.SHOW_ALL_CAVE_ENEMIES || (this.SHOW_DISCOVERED_ENEMIES && hasDiscovered)) ? "W" : symbol;
            color = (this.SHOW_ALL_CAVE_ENEMIES || (this.SHOW_DISCOVERED_ENEMIES && hasDiscovered)) ? Color.RED : color;
        } else if (enemyClass.equals(Arrow.class)){
            symbol = (this.SHOW_ALL_CAVE_ENEMIES) ? "F" : symbol;
            color = (this.SHOW_ALL_CAVE_ENEMIES) ? Color.GREEN : color;
        }

        return new MazeElement(symbol, x, y, color, hasVisible);
    }

    public void updateElementSymbol(Cave cave) {
        MazeElement currentElement = elements.get(cave.toString());
        MazeElement updatedElement = this.defineMazeElementToCave(cave, currentElement.x, currentElement.y);
        elements.put(cave.toString(), updatedElement);
    }

    public void updateElementSymbol(Cave cave, String symbol, Color color) {
        MazeElement mazeElement = elements.get(cave.toString());
        mazeElement.symbol = symbol;
        mazeElement.color = color;
        elements.put(cave.toString(), mazeElement);
    }

    public void updateElementColor(String elementId, Color color){
        MazeElement mazeElement = elements.get(elementId);
        mazeElement.color = color;
        elements.put(elementId, mazeElement);
    }

    public void registerPlayerMovement(Cave from, Cave to) {
        elements.get(from.toString()).visible = true;
        elements.get(from.toString()).discovered = true;
        this.updateElementSymbol(from);

        elements.get(to.toString()).visible = true;
        elements.get(to.toString()).discovered = true;
        this.updateElementSymbol(to, "P", Color.YELLOW);

        if (this.TRACK_PLAYER_MOVEMENT){
            MazeElement mazeElement = elements.get(from + to.toString());
            if (mazeElement != null){
                mazeElement.visible = true;
                mazeElement.discovered = true;
                updateElementColor(from + to.toString(), Color.CYAN);
            } else if (elements.get(to + from.toString()) != null) {
                elements.get(to + from.toString()).visible = true;
                elements.get(to + from.toString()).discovered = true;
                updateElementColor(to + from.toString(), Color.CYAN);
            }

            if ((!this.SHOW_ALL_CAVE_ENEMIES && !this.SHOW_DISCOVERED_ENEMIES) || elements.get(from.toString()).symbol.equals("C")){
                updateElementColor(from.toString(), Color.CYAN);
            }
        }
    }

    public void print() {
        if (!this.SHOW_MAZE){
            return;
        }

        int mapHeight = getHeight();
        int mapWidth = getWidth();
        String[][] map = new String[mapHeight][mapWidth];

        for (MazeElement mazeElement : elements.values()){
            if (mazeElement.visible){
                map[Math.abs(mazeElement.y - fixY)][mazeElement.x + fixX] = (mazeElement.color != null ? mazeElement.color.value() : "") + mazeElement.symbol + Color.RESET.value();
            }
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
