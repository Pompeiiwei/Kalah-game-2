package moveSeeds;

import com.qualitascorpus.testsupport.IO;
import player.Player;

public class MovingLogic {
    private IO io;
    private final int NUMBER_PER_MOVE = 1;
    private Player[] players;
    private Player playerInOperation;
    private Player player;
    private Player opposite;
    private boolean anotherMove;
    private int playerNumberToMove;


    public MovingLogic(IO io,Player[]players) {
        this.io = io;
        this.players=players;
    }

    public boolean notAnotherMove() {
        return !anotherMove;
    }

    public int getPlayerNumberToMove() {
        return playerNumberToMove;
    }

    public void setPlayerNumberToMove(int playerNumberToMove) {
        this.playerNumberToMove = playerNumberToMove;
    }

    public void move(int houseNumber) {
        anotherMove = false;
        playerInOperation = players[playerNumberToMove - 1];
        int houseIndex = houseNumber - 1;
        player = players[playerNumberToMove - 1];
        opposite = players[oppositePlayer(playerNumberToMove) - 1];
        int seedsToMove = player.getPlayersHouses()[houseIndex].getSeedsCount();
        if (seedsToMove == 0) {
            io.println("House is empty. Move again.");
            anotherMove = true;
        } else { // if seeds in the house is not 0 then we can start to sow
            houseNumber = processOfSowSeeds(houseIndex, false);
            anotherMove(houseNumber);
            capture(houseNumber);
        }
        changePlayerToMove();
    }

    public void changePlayerToMove(){
            if(notAnotherMove())
                playerNumberToMove = oppositePlayer(playerNumberToMove);
    }

    public void anotherMove(int houseNumber){
        if (houseNumber == player.getNumberOfHouses() + 1){
            if (player.equals(playerInOperation))
                anotherMove = true; // player in operation gets another move
        }
    }

    public void capture(int houseNumber){
        if (player.equals(playerInOperation)
                && houseNumber <= player.getNumberOfHouses()
                && opposite.getPlayersHouses()[player.getNumberOfHouses()- houseNumber].getSeedsCount() != 0
                && player.getPlayersHouses()[houseNumber - 1].getSeedsCount() == 1 // Capture
        ) {
            int takeOppositeSeed = opposite.getPlayersHouses()[player.getNumberOfHouses() - houseNumber].realTakeSeeds();
            int playerHousePoint = player.getPlayersHouses()[houseNumber-1].realTakeSeeds();
            player.getPlayerStore().sowSeeds(takeOppositeSeed + playerHousePoint);
        }
    }

    public int oppositePlayer(int playerNumber){
        if (playerNumber% players.length == 0){
            return 1; // if player2 in operation then change to player1
        }else{
            return 2; // if player1 in operation then change to player2
        }
    }

    public int processOfSowSeeds(int houseIndex,boolean simulation){
        int houseNumber = houseIndex + 1;
        int seedsToMove =  takeSeedsHouse(houseIndex,simulation);

        while (seedsToMove > 0){
            while (seedsToMove > 0 && houseNumber < player.getNumberOfHouses()){
                houseNumber = player.getPlayersHouses()[houseNumber].sowSeedsHouse(houseNumber,simulation,NUMBER_PER_MOVE);
                seedsToMove -= NUMBER_PER_MOVE;
            }
            if (seedsToMove > 0 && houseNumber == player.getNumberOfHouses()){// add seeds in player in operation 's Store
                if (player.equals(playerInOperation)){
                    player.getPlayerStore().sowSeedsStore(simulation,NUMBER_PER_MOVE);
                    seedsToMove -= NUMBER_PER_MOVE;
                }
                houseNumber++;
            }
            if (seedsToMove > 0){// change the houses belong to opposite (add seeds to opposite)
                houseNumber = changeSowingToOpposite();
            }
        }
        return houseNumber;
    }

    public int takeSeedsHouse(int houseIndex,boolean simulation){
        return player.getPlayersHouses()[houseIndex].takeSeeds(simulation);
    }

    public int changeSowingToOpposite(){
        Player originalPlayer = player;
        player = opposite;
        opposite = originalPlayer;
        return 0;
    }
}
