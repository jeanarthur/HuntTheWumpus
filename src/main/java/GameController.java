import java.util.Map;

public class GameController {

    Output output;
    Input input;

    public GameController(){
        this.output = new Output();
        this.input = new Input();
    }

    public void start() {
        Menu menu = new Menu();
        MenuOptions menuOption = menu.show();

        if (menuOption == MenuOptions.PLAY) {
            play();
        }
    }

    private void play() {

        Player player = new Player();

        Maze map = new Maze();
        map.generate();
        map.setPlayer(player);


        while (true) {
            Map<String, ValidInput> validInputMap = input.getValidInputMap(player.currentCave);

            ValidInput userValidInput = null;
            while (userValidInput == null) {
                output.showSeparator();
                map.print();
                output.showSeparator();
                output.requestAction();
                output.listValidInputs(validInputMap);
                output.requestInput();

                String option = input.get();
                userValidInput = validInputMap.get(option);

                if (userValidInput == null){
                    output.showInputError();
                }
            }

            if (userValidInput.value != null){
                map.playerMoveTo((Coordinates) userValidInput.value);
            }

        }

    }


}
