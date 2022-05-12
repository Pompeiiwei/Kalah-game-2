package save;

import board.Board;
import gameController.GameController;

public class Save {
    private GameController gameController;
    private Board board;


    public Save(GameController gameController, Board board) {
        this.gameController = gameController;
        this.board = board;
    }

    public void saveGame(){
        gameController.setMemento(board.saveToMemento());
        board.printBoard();
    }
}
