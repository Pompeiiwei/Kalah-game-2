package load;

import board.Board;
import gameController.GameController;

public class Load {
    private GameController gameController;
    private Board board;

    public Load(GameController gameController, Board board) {
        this.gameController = gameController;
        this.board = board;
    }

    public void loadGame(){
        if (gameController.getMemento() == null){
            board.getIo().println("No saved game");
            board.printBoard();
        }else{
            board.restoreFromMemento(gameController.getMemento());
        }
    }
}
