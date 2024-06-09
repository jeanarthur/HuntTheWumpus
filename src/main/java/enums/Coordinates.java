package enums;

public enum Coordinates {

    EAST,
    WEST,
    NORTH,
    SOUTH;

    public static Coordinates getOpposite(Coordinates coordinate){
        if (coordinate == null){
            return null;
        }

        switch (coordinate){
            case NORTH -> {
                return SOUTH;
            }
            case SOUTH -> {
                return NORTH;
            }
            case EAST -> {
                return WEST;
            }
            case WEST -> {
                return EAST;
            }
        }

        return null;
    }

    public static Coordinates getNextClockwiseCoordinate(Coordinates coordinate){
        switch (coordinate){
            case NORTH -> {
                return EAST;
            }
            case SOUTH -> {
                return WEST;
            }
            case EAST -> {
                return SOUTH;
            }
            case WEST -> {
                return NORTH;
            }
        }

        return null;
    }

}
