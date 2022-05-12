package command;

import gameController.GameController;

public class QuitGameCommand implements Command{

    private GameController gameController;

    public QuitGameCommand(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        gameController.quitGame();
    }
}
