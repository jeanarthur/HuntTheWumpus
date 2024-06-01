import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualMap {

    Map<String, MapElement> elements;

    private int fixX;
    private int fixY;

    public VisualMap(Cave root) {
        this.elements = new HashMap<>();
        this.elements.put(root.toString(), new MapElement("C", 0, 0));
    }

    public void createVisualConnection(Cave from, Cave to) {
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

    public void print() {
        int mapHeight = getHeight();
        int mapWidth = getWidth();
        String[][] map = new String[mapHeight][mapWidth];

        System.out.printf("Dimensões: %d x %d\n", mapHeight, mapWidth);

        for (MapElement mapElement : elements.values()){
            map[Math.abs(mapElement.y - fixY)][mapElement.x + fixX] = mapElement.symbol;
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
