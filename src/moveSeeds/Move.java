package moveSeeds;

import board.Board;

public class Move {
    private MovingLogic movingLogic;
    private Board board;


    public Move(MovingLogic moving, Board board) {
        this.movingLogic = moving;
        this.board = board;

    }

    public void makeMove(String input){
        try {
            int houseNumber = Integer.parseInt(input);
            movingLogic.setPlayerNumberToMove(board.getPlayerNumberToMove());
            movingLogic.move(houseNumber);
            board.setPlayerNumberToMove(movingLogic.getPlayerNumberToMove());
            board.printBoard();

            if(!board.canMove()){
                board.getIo().println("Game over");
                board.printBoard();
                board.printScores();
            }
        }catch (Exception e){
            board.getIo().println("Invalid command. Try again.");
        }
    }
}
