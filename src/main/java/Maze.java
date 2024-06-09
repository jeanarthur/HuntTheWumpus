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

    public VisualMaze getVisualMaze() {
        return visualMaze;
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

        this.addEnemies();
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

    private void addEnemies(){
        MazeNavigator mazeNavigator = new MazeNavigator();
        List<Cave> caveList = mazeNavigator.getCaveList(this.root);
        caveList.remove(this.root);

        int enemyProportion_bat = (int)Math.floor(this.size * 0.25);
        int limitEnemy_bat = randInt((int)(Math.ceil(enemyProportion_bat * 0.5)), enemyProportion_bat);

        int batCount = 0;
        while (batCount < limitEnemy_bat) {
            Cave cave = caveList.get(randInt(0, caveList.size() - 1));
            if (cave.getEnemy() == null){
                cave.setEnemy(new Bat());
                caveList.remove(cave);
                batCount++;
                visualMaze.updateElementSymbol(cave);
            }
        }

        int enemyProportion_hole = (int)Math.floor(this.size * 0.15);
        int limitEnemy_hole = randInt((int)(Math.ceil(enemyProportion_hole * 0.5)), enemyProportion_hole);

        int holeCount = 0;
        while (holeCount < limitEnemy_hole) {
            Cave cave = caveList.get(randInt(0, caveList.size() - 1));
            if (cave.getEnemy() == null){
                cave.setEnemy(new Hole());
                caveList.remove(cave);
                holeCount++;
                visualMaze.updateElementSymbol(cave);
            }
        }

        Cave cave = caveList.get(randInt(0, caveList.size() - 1));
        cave.setEnemy(new Wumpus());
        visualMaze.updateElementSymbol(cave);
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
