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
}
