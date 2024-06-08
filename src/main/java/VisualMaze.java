import java.util.HashMap;
import java.util.Map;

public class VisualMaze {

    Map<String, MapElement> elements;
    private boolean TRACK_PLAYER_MOVEMENT;

    private int fixX;
    private int fixY;

    public VisualMaze(Cave root) {
        this.elements = new HashMap<>();
        this.elements.put(root.toString(), new MapElement("C", 0, 0));

        this.TRACK_PLAYER_MOVEMENT = true;
    }

    public void createVisualConnection(Cave from, Cave to) {
        if (to == null){
            return;
        }

        if (!elements.containsKey(from.toString())) {
            throw new IllegalArgumentException("Parameter 'from' can be previous registered on caveElements");
        }

        MapElement fromElement = elements.get(from.toString());

        if (from.getNorth() != null && from.getNorth().equals(to)){
            elements.put(from + to.toString(), new MapElement("|", fromElement.x, fromElement.y + 1));
            elements.put(to.toString(), new MapElement("C", fromElement.x, fromElement.y + 2));
        }
        else if (from.getSouth() != null && from.getSouth().equals(to)) {
            elements.put(from + to.toString(), new MapElement("|", fromElement.x, fromElement.y - 1));
            elements.put(to.toString(), new MapElement("C", fromElement.x, fromElement.y - 2));
        }
        else if (from.getEast() != null && from.getEast().equals(to)) {
            elements.put(from + to.toString(), new MapElement("-", fromElement.x + 1, fromElement.y));
            elements.put(to.toString(), new MapElement("C", fromElement.x + 2, fromElement.y));
        }
        else if (from.getWest() != null && from.getWest().equals(to)) {
            elements.put(from + to.toString(), new MapElement("-", fromElement.x - 1, fromElement.y));
            elements.put(to.toString(), new MapElement("C", fromElement.x - 2, fromElement.y));
        }
        else {
            throw new IllegalArgumentException("Parameter 'to' not has connected to Cave provided in 'from' parameter");
        }
    }

    public void updateElementSymbol(Cave cave, String symbol) {
        MapElement mapElement = elements.get(cave.toString());
        mapElement.symbol = symbol;
        elements.put(cave.toString(), mapElement);
    }

    public void updateElementSymbol(Cave cave, String symbol, Color color) {
        MapElement mapElement = elements.get(cave.toString());
        mapElement.symbol = symbol;
        mapElement.color = color;
        elements.put(cave.toString(), mapElement);
    }

    public void updateElementSymbol(String elementId, String symbol, Color color){
        MapElement mapElement = elements.get(elementId);
        mapElement.symbol = symbol;
        mapElement.color = color;
        elements.put(elementId, mapElement);
    }

    public void updateElementColor(String elementId, Color color){
        MapElement mapElement = elements.get(elementId);
        mapElement.color = color;
        elements.put(elementId, mapElement);
    }

    public void registerPlayerMovement(Cave from, Cave to) {
        this.updateElementSymbol(from, "C", Color.RESET);
        this.updateElementSymbol(to, "P", Color.YELLOW);

        if (this.TRACK_PLAYER_MOVEMENT){
            MapElement mapElement = elements.get(from + to.toString());
            if (mapElement != null){
                updateElementColor(from + to.toString(), Color.CYAN);
            } else {
                updateElementColor(to + from.toString(), Color.CYAN);
            }
            updateElementColor(from.toString(), Color.CYAN);
        }
    }

    public void print() {
        int mapHeight = getHeight();
        int mapWidth = getWidth();
        String[][] map = new String[mapHeight][mapWidth];

        // System.out.printf("Dimensões: %d x %d\n", mapHeight, mapWidth);

        for (MapElement mapElement : elements.values()){
            map[Math.abs(mapElement.y - fixY)][mapElement.x + fixX] = (mapElement.color != null ? mapElement.color.value() : "") + mapElement.symbol + Color.RESET.value();
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

        for (MapElement mapElement : elements.values()){
            minX = Math.min(minX, mapElement.x);
            maxX = Math.max(maxX, mapElement.x);
        }

        fixX = Math.abs(minX);
        return Math.abs(minX) + maxX + 1;
    }

    private int getHeight() {
        int minY = 0;
        int maxY = 0;

        for (MapElement mapElement : elements.values()){
            minY = Math.min(minY, mapElement.y);
            maxY = Math.max(maxY, mapElement.y);
        }

        fixY = maxY;
        return Math.abs(minY) + maxY + 1;
    }
}
