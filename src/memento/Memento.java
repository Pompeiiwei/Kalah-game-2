package memento;

import player.Player;

public class Memento {
    private Player[] players;
    private int playerNumberToMove;

    public Memento(Player[] players, int playerNumberToMove) {
        this.players = players;
        this.playerNumberToMove = playerNumberToMove;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayerNumberToMove() {
        return playerNumberToMove;
    }
}
