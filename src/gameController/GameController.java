package gameController;

import board.Board;
import com.qualitascorpus.testsupport.IO;
import command.*;
import load.Load;
import memento.Memento;
import player.Player;
import moveSeeds.Move;
import moveSeeds.MovingLogic;
import save.Save;
import java.util.ArrayList;

public class GameController {
    boolean gameOver = false;
    ArrayList<Command> commandList;
    private Memento memento;
    private IO io;
    private MovingLogic movingLogic;
    private Board board;
    private Move move;
    private Save save;
    private Load load;
    private Player[] players;

    public GameController(IO io,Player[]players) {
        commandList = new ArrayList<>();
        this.io = io;
        this.players = players;
        board = new Board(io,players,1);
        movingLogic = new MovingLogic(io,players);
        memento = null;
        save = new Save(this,board);
        load = new Load(this,board);
        move = new Move(movingLogic,board);
    }

    public void gameStart() {
        board.printBoard();
        while (board.canMove() && !gameOver) {
            commandList.clear();
            String prompt = board.getPrompt();
            String input = io.readFromKeyboard(prompt);

            addToCommandQueue(input);
            executeCommand();
        }
    }

    public void addToCommandQueue(String input){
        switch (input.toLowerCase()){
            case "n":
                Command newGameCommand = new NewGameCommand(this);
                commandList.add(newGameCommand);
                break;
            case "q":
                Command quitGameCommand = new QuitGameCommand(this);
                commandList.add(quitGameCommand);
                break;
            case "s":
                Command saveGameCommand = new SaveGameCommand(save);
                commandList.add(saveGameCommand);
                break;
            case "l":
                Command loadGameCommand = new LoadGameCommand(load);
                commandList.add(loadGameCommand);
                break;
            default:
                Command moveCommand = new MoveCommand(move,input);
                commandList.add(moveCommand);
                break;
        }
    }

    public void executeCommand(){
        for (Command command:commandList)
            command.execute();
    }

    public Memento getMemento() {
        return memento;
    }

    public void quitGame(){
        this.gameOver = true;
        io.println("Game over");
        board.printBoard();
    }

    public void restartGame(){
        gameOver = true;
        initGameState();
        gameStart();
    }

    public void initGameState(){
        gameOver = false;
        this.players = new Player[2];
        for (int i = 0; i < 2; i++) {
            players[i] = new Player(6,4,0);
        }
        board = new Board(io,players,1);
        movingLogic = new MovingLogic(io,players);
        move = new Move(movingLogic,board);
        setMemento(null);
        save = new Save(this,board);
        load = new Load(this,board);
    }

    public void setMemento(Memento Memento) {
        this.memento = Memento;
    }
}
