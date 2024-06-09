public class MazeElement {

    String symbol;
    Color color;
    int x;
    int y;
    boolean visible;
    boolean discovered;

    public MazeElement(String symbol, int x, int y, boolean visible) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.visible = visible;
        this.discovered = false;
    }

    public MazeElement(String symbol, int x, int y, Color color, boolean visible) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.color = color;
        this.visible = visible;
        this.discovered = false;
    }
}
