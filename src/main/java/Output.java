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

    public void showDiedByWumpusMessage() {
        System.out.println("O Wumpus capturou o player");
    }

    public void showDefeatedByHoleMessage() {
        System.out.println("O Player caiu em um poço sem fundo");
    }

    public void showNearWumpusMessage() {
        System.out.println("O player consegue sentir o odor");
    }


    public void showNearBatMessage() {
        System.out.println("O player consegue ouvir o bater das asas");
    }

    public void showNearHoleMessage() {
        System.out.println("O player consegue sentir a brisa");
    }

    public void showMovedByBatMessage() {
        System.out.println("Player foi pego por um morcego e levado a uma caverna aleatória");
    }

    public void showPlayerGetArrowMessage() {
        System.out.println("Player encontrou uma flecha");
    }

    public void showPlayerArrowCount(Player player){
        System.out.printf("O player possui %d flecha(s)\n", player.arrows);
    }

    public void showInsuficientArrows() {
        System.out.println("Player não possui flechas");
    }

    public void showDefeatedWumpusMessage() {
        System.out.println("Capturou o Wumpus");
    }

    public void showDefeatedBatMessage() {
        System.out.println("Eliminou um morcego");
    }

    public void showNoArrowsAvailableMessage() {
        System.out.println("Acabou as flechas do player e não há mais flechas no mapa");
    }
}
