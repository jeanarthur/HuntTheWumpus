import java.util.List;
import java.util.Map;

public class Output {

    public void showMenu() {
        System.out.println("-- Hunt the Wumpus --");
        System.out.println("  1. Jogar           ");
        System.out.println("  2. Regras          ");
        System.out.println("  3. Sair            ");
        System.out.println("---------------------");
    }

    public void requestInput() {
        System.out.print("Digite: ");
    }

    public void showInputError() {
        System.out.println("Opção inválida, tente novamente");
    }

    public void showRules() {
        System.out.println("-- Regras --");
    }

    public void showExitMessage() {
        System.out.println("Jogo encerrado");
    }

    public void startGameMessage() {
        System.out.println("Jogo iniciado");
    }

    public void listValidInputs(Map<String, ValidInput> validInputMap) {
        for (ValidInput validInput : validInputMap.values()){
            System.out.printf("%s -> %s\n", validInput.command, validInput.description);
        }
    }

    public void showSeparator() {
        System.out.println("========================");
    }
    public void requestAction() {
        System.out.println("Escolha uma ação");
    }

}
