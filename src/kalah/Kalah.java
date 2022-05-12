package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import gameController.GameController;
import player.Player;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		Player[]players = new Player[2];
		for (int i = 0; i < 2; i++)
			players[i] = new Player(6,4,0);

		GameController gameController = new GameController(io,players);
		gameController.gameStart();
	}



	public void play(IO io, boolean vertical, boolean emptyHouseCapture) {
		// DO NOT CHANGE. Only here for backwards compatibility
	        play(io);
	}

        public void play(IO io, boolean vertical, boolean emptyHouseCapture, boolean bmf) {
		// DO NOT CHANGE. Only here for backwards compatibility
	        play(io);
	}
}
