public class Cave {

    private Cave north;
    private Cave south;
    private Cave east;
    private Cave west;
    private Enemy enemy;

    public Cave() {
    }

    public Cave(Cave north, Cave south, Cave east, Cave west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public Cave getEast() {
        return east;
    }

    public Cave getWest() {
        return west;
    }

    public Cave getNorth() {
        return north;
    }

    public Cave getSouth() {
        return south;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEast(Cave east) {
        this.east = east;
    }

    public void setWest(Cave west) {
        this.west = west;
    }

    public void setNorth(Cave north) {
        this.north = north;
    }

    public void setSouth(Cave south) {
        this.south = south;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Cave get(Coordinates coordinate){
        switch (coordinate){
            case NORTH -> {
                return this.getNorth();
            }
            case SOUTH -> {
                return this.getSouth();
            }
            case EAST -> {
                return this.getEast();
            }
            case WEST -> {
                return this.getWest();
            }
        }

        return null;
    }

    public void set(Cave cave, Coordinates coordinate) {
        switch (coordinate){
            case NORTH -> this.north = cave;
            case SOUTH -> this.south = cave;
            case EAST -> this.east = cave;
            case WEST -> this.west = cave;
        }
    }

    public String objectToString() {
        return this + "{" +
                "north=" + north +
                ", south=" + south +
                ", east=" + east +
                ", west=" + west +
                ", enemy=" + enemy +
                '}';
    }
}
