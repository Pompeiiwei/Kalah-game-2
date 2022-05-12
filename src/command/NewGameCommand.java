package command;

import gameController.GameController;

public class NewGameCommand implements Command{

    private GameController gameController;

    public NewGameCommand(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        gameController.restartGame();
    }
}
