public class Player {

    Cave currentCave;
    int arrows;

    public Player(){
        this.arrows = 1;
    }

    public void navigate(Coordinates coordinates) {
        this.currentCave = currentCave.get(coordinates);
    }

}
