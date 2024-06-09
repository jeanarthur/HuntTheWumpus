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

    public void requestGameModeSelection() {
        System.out.println("Escolha um modo de jogo");
    }

    public void showInputError() {
        System.out.println("Opção inválida, tente novamente");
    }

    public void showRules() {
        System.out.println("-- Regras --");
        System.out.println("""
                Para iniciar selecione Jogar e escolha o modo
                Seu objetivo é atirar uma flecha no Wumpus, você inicia com 1 flecha e pode encontrar outras nas cavernas
                Navegue pelas cavernas usando 'w','a','s' ou 'd' + Enter para cada movimento
                use 'e' + Enter para atirar uma flecha e escolha a direção com 'w','a','s' ou 'd' + Enter
                ---
                Algumas dicas sobre as cavernas adjacentes são mostradas conforme caminha
                morcegos te levarão para cavernas aleatórias
                poços sem fundo são letais, assim como encontrar o Wumpus
                ---
                Divirta-se!""");
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

    public void showDefeatedByWumpusMessage() {
        System.out.println(Color.RED.value() + "O Wumpus capturou o player" + Color.RESET.value());
    }

    public void showDefeatedByHoleMessage() {
        System.out.println(Color.RED.value() + "O Player caiu em um poço sem fundo" + Color.RESET.value());
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
        System.out.println(Color.GREEN.value() + "Player encontrou uma flecha" + Color.RESET.value());
    }

    public void showPlayerArrowCount(Player player){
        System.out.printf("O player possui %d flecha(s)\n", player.arrows);
    }

    public void showInsuficientArrows() {
        System.out.println("Player não possui flechas");
    }

    public void showDefeatedWumpusMessage() {
        System.out.println(Color.GREEN.value() + "Capturou o Wumpus" + Color.RESET.value());
    }

    public void showNoArrowsAvailableMessage() {
        System.out.println(Color.RED.value() + "Acabou as flechas do player e não há mais flechas no mapa" + Color.RESET.value());
    }
}
