public class GameController {

    public void start() {
        Menu menu = new Menu();
        MenuOptions menuOption = menu.show();

        if (menuOption == MenuOptions.PLAY) {
            play();
        }
    }

    private void play() {

        Player player = new Player();

        Map map = new Map();
        map.generate();
        map.setPlayer(player);

        map.print();

    }


}
