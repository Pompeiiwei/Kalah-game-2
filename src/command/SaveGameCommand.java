package command;

import save.Save;

public class SaveGameCommand implements Command{

    private Save save;

    public SaveGameCommand(Save save) {
        this.save = save;
    }

    @Override
    public void execute() {
        save.saveGame();
    }
}
