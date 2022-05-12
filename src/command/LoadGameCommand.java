package command;

import load.Load;

public class LoadGameCommand implements Command{
    private Load load;

    public LoadGameCommand(Load load) {
        this.load = load;
    }

    @Override
    public void execute() {
        load.loadGame();
    }
}
