package command;

import moveSeeds.Move;


public class MoveCommand implements Command{
    private Move move;
    private String input;

    public MoveCommand(Move move,String input) {
        this.move = move;
        this.input = input;
    }

    @Override
    public void execute() {
        move.makeMove(input);
    }
}
