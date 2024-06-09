import java.util.Map;

public class Menu {

    Output output = new Output();
    Input input = new Input();

    public MenuOptions show() {
        output.showMenu();
        output.requestInput();

        int option = -1;
        try {
            option = input.getInteger();
        } catch (NumberFormatException e) {
            output.showInputError();
            return show();
        }

        switch (option){
            case 1 -> {
                output.startGameMessage();
                return MenuOptions.PLAY;
            }
            case 2 -> {
                output.showRules();
                return show();
            }
            case 3 -> {
                output.showExitMessage();
                return MenuOptions.EXIT;
            }
            default -> {
                output.showInputError();
                return show();
            }
        }
    }

    public GameModes showGameModes() {
        Map<String, ValidInput> validInputMap = input.getValidGameModeInput();

        ValidInput validInput = null;
        while (validInput == null){
            output.requestGameModeSelection();
            output.listValidInputs(validInputMap);
            output.requestInput();

            validInput = validInputMap.get(input.get());

            if (validInput != null && validInput.value != null){
                return (GameModes)validInput.value;
            }

            output.showInputError();
        }

        return GameModes.FULL_MAP;
    }

}
