import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    Cave root;
    int size;
    private VisualMaze visualMaze;
    private Player player;

    public Maze() {
        this.root = new Cave();
        this.size = 1;

        this.visualMaze = new VisualMaze(this.root);
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.player.currentCave = this.root;
        this.visualMaze.updateElementSymbol(this.root, "P", Color.YELLOW);
    }

    public int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public void generate() {
        Coordinates[] coordinates = Coordinates.values();
        List<Cave> caves = new ArrayList<>();
        caves.add(this.root);

        int limitSize = randInt(20, 30);
        // System.out.println("Cave expected size: " + limitSize);
        // System.out.println("1 - " + this.root.objectToString());
        while (this.size < limitSize){
            int iterations = randInt(1, 4);
            Cave currentParent = caves.remove(0);

            for (int i = 1; (i < iterations || caves.isEmpty()) && this.size < limitSize; i++) {
                int coordinateIndex = randInt(0, 3);
                Cave inserted = insertNewCave(currentParent, coordinates[coordinateIndex]);
                if (inserted != null){
                    caves.add(inserted);
                }
            }
        }
        // System.out.println("Cave real size: " + this.size);
    }

    private Cave insertNewCave(Cave parent, Coordinates coordinate) {
        if (parent.get(coordinate) != null){
            return null;
        }

        Cave newCave = new Cave();
        this.connectCaves(parent, newCave, coordinate);

        this.size++;
        // System.out.println(this.size + " - " + newCave.objectToString());
        return newCave;
    }

    private void connectCaves(Cave parent, Cave newCave, Coordinates coordinate){
        Coordinates parentCoordinate = Coordinates.getOpposite(coordinate);

        if (parent == null || (parent.get(coordinate) != null && parent.get(coordinate).equals(newCave))){
            return;
        }

        parent.set(newCave, coordinate);
        newCave.set(parent, parentCoordinate);
        this.visualMaze.createVisualConnection(parent, newCave);

        Coordinates parentClockwiseCoordinate = Coordinates.getNextClockwiseCoordinate(coordinate);
        Coordinates parentOppositeClockwiseCoordinate = Coordinates.getOpposite(parentClockwiseCoordinate);

        Cave parentClockwiseCoordinateCave = parent.get(parentClockwiseCoordinate);
        if (parentClockwiseCoordinateCave != null){
            connectCaves(parentClockwiseCoordinateCave.get(coordinate), newCave, parentOppositeClockwiseCoordinate);
        }

        Cave parentOppositeClockwiseCoordinateCave = parent.get(parentOppositeClockwiseCoordinate);
        if (parentOppositeClockwiseCoordinateCave != null){
            connectCaves(parentOppositeClockwiseCoordinateCave.get(coordinate), newCave, parentClockwiseCoordinate);
        }
    }

    public VisualMaze getVisualMaze() {
        return visualMaze;
    }

    public void playerMoveTo (Coordinates coordinate){
        Cave from = this.player.currentCave;
        this.player.navigate(coordinate);
        this.visualMaze.registerPlayerMovement(from, this.player.currentCave);
    }

    public void print() {
        this.visualMaze.print();
    }

}
