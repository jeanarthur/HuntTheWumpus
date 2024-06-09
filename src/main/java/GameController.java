import java.util.Map;

public class GameController {

    Output output;
    Input input;
    GameStatus gameStatus;

    public GameController(){
        this.output = new Output();
        this.input = new Input();
    }

    public void start() {
        Menu menu = new Menu();
        MenuOptions menuOption = null;
        while (menuOption != MenuOptions.EXIT){
            menuOption = menu.show();
            if (menuOption == MenuOptions.PLAY) {
                GameModes gameMode = menu.showGameModes();
                play(gameMode);
            }
        }
    }

    private void play(GameModes gameMode) {

        Player player = new Player();

        Maze map = new Maze(gameMode);
        map.generate();
        map.setPlayer(player);

        this.gameStatus = GameStatus.RUNNING;
        while (this.gameStatus == GameStatus.RUNNING) {
            Map<String, ValidInput> validInputMap = input.getValidInputMap(player.currentCave);

            ValidInput userValidInput = null;
            while (userValidInput == null) {

                output.showSeparator();
                map.print();
                output.showSeparator();
                reportStatus(map);

                if (this.gameStatus != GameStatus.RUNNING){
                    break;
                }

                output.requestAction();
                output.listValidInputs(validInputMap);
                output.requestInput();

                String option = input.get();
                userValidInput = validInputMap.get(option);

                if (userValidInput == null){
                    output.showInputError();
                }
            }

            if (this.gameStatus == GameStatus.CAPTURED_BY_BAT){
                this.gameStatus = GameStatus.RUNNING;
                continue;
            }

            if (this.gameStatus != GameStatus.RUNNING){
                break;
            }

            if (userValidInput != null && userValidInput.value != null){
                map.playerMoveTo((Coordinates) userValidInput.value);
            } else if (userValidInput != null) {
                Map<String, ValidInput> validInputToArrow = input.getValidInputToArrow(player.currentCave);
                userValidInput = null;
                while (userValidInput == null) {
                    output.showSeparator();
                    map.print();
                    output.showSeparator();
                    reportStatus(map);

                    if (this.gameStatus == GameStatus.CAPTURED_BY_BAT){
                        this.gameStatus = GameStatus.RUNNING;
                        continue;
                    }

                    if (this.gameStatus != GameStatus.RUNNING){
                        break;
                    }

                    output.requestAction();
                    output.listValidInputs(validInputToArrow);
                    output.requestInput();

                    String option = input.get();
                    userValidInput = validInputMap.get(option);

                    if (userValidInput == null){
                        output.showInputError();
                    }

                    if (userValidInput != null && userValidInput.value != null){
                        fireArrow(player, (Coordinates) userValidInput.value);
                    }
                }
            }

        }

    }

    private void reportStatus(Maze maze){
        Cave current = maze.getPlayer().currentCave;

        Enemy currentEnemy = current.getEnemy();
        if (currentEnemy != null && currentEnemy.getClass().equals(Wumpus.class)){
            output.showDefeatedByWumpusMessage();
            this.gameStatus = GameStatus.DEFEATED_BY_WUMPUS;
            return;
        } else if (currentEnemy != null && currentEnemy.getClass().equals(Hole.class)){
            output.showDefeatedByHoleMessage();
            this.gameStatus = GameStatus.DEFEATED_BY_HOLE;
            return;
        } else if (currentEnemy != null && currentEnemy.getClass().equals(Bat.class)){
            output.showMovedByBatMessage();
            maze.putPlayerInRandomCave();
            this.gameStatus = GameStatus.CAPTURED_BY_BAT;
            return;
        } else if (currentEnemy != null && currentEnemy.getClass().equals(Arrow.class)){
            output.showPlayerGetArrowMessage();
            maze.getPlayer().arrows++;
            maze.cavesWithArrows--;
            current.setEnemy(null);
        } else if (maze.cavesWithArrows == 0 && maze.getPlayer().arrows == 0){
            output.showNoArrowsAvailableMessage();
            this.gameStatus = GameStatus.NO_ARROWS_AVAILABLE;
            return;
        }

        for(Coordinates coordinate : Coordinates.values()){
            Cave cave = current.get(coordinate);
            if (cave == null){
                continue;
            }

            Enemy enemy = cave.getEnemy();
            if (enemy == null){
                continue;
            }

            if (enemy.getClass().equals(Wumpus.class)) {
                output.showNearWumpusMessage();
            } else if (enemy.getClass().equals(Bat.class)) {
                output.showNearBatMessage();
            } else if (enemy.getClass().equals(Hole.class)) {
                output.showNearHoleMessage();
            }
        }

        output.showPlayerArrowCount(maze.getPlayer());

    }

    private void fireArrow(Player player, Coordinates coordinate) {
        if (player.arrows < 1){
            output.showInsuficientArrows();
            return;
        }

        player.arrows--;
        Cave cave = player.currentCave.get(coordinate);
        Enemy enemy = cave.getEnemy();
        if (enemy != null && enemy.getClass().equals(Wumpus.class)){
            output.showDefeatedWumpusMessage();
            this.gameStatus = GameStatus.WIN;
        }

    }

}
