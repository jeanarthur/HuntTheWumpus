public class MazeElement {

    String symbol;
    Color color;
    int x;
    int y;

    public MazeElement(String symbol, int x, int y) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public MazeElement(String symbol, int x, int y, Color color) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
