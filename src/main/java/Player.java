public class Player {

    Cave currentCave;

    public void navigate(Coordinates coordinates) {
        this.currentCave = currentCave.get(coordinates);
    }



}
