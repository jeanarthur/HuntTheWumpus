package entities;

import enums.Coordinates;
import maze.Cave;

public class Player {

    public Cave currentCave;
    public int arrows;

    public Player(){
        this.arrows = 1;
    }

    public void navigate(Coordinates coordinates) {
        this.currentCave = currentCave.get(coordinates);
    }

}
