public class Menu {

    Output output = new Output();
    Input input = new Input();

    public void show() {
        output.showMenu();
        output.requestInput();

        int option = -1;
        try {
            option = input.getInteger();
        } catch (NumberFormatException e) {
            output.showInputError();
            show();
            return;
        }

        switch (option){
            case 1 -> output.startGameMessage();
            case 2 -> {
                output.showRules();
                show();
            }
            case 3 -> output.showExitMessage();
            default -> {
                output.showInputError();
                show();
            }
        }

    }

}
