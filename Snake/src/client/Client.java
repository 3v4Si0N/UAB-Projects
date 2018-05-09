package client;

import controller.DefaultGameController;

/**
 * Client class to execute the game
 * @author Ramon Guimer�, H�ctor De Armas
 *
 */
public class Client {

    /**
     * Creates a game controller and executes play
     * @param args NULL
     */
	public static void main(String[] args) {
		DefaultGameController gameController = new DefaultGameController();
        gameController.play();
	}
}
