public class MapElement {

    String symbol;
    Color color;
    int x;
    int y;

    public MapElement(String symbol, int x, int y) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public MapElement(String symbol, int x, int y, Color color) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
