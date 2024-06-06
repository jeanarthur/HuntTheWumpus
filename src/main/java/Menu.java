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

}
