import java.util.Objects;

public class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return String.format("%d,%d", this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.toString(), obj.toString());
    }

    public static Coordinate next(Coordinate origin, Coordinates direction){
        Coordinate next = null;
        switch (direction){
            case NORTH -> next = new Coordinate(origin.x, origin.y + 1);
            case SOUTH -> next = new Coordinate(origin.x, origin.y - 1);
            case EAST -> next = new Coordinate(origin.x + 1, origin.y);
            case WEST -> next = new Coordinate(origin.x - 1, origin.y);
        }

        return next;
    }
}
