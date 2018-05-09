package main;

import game.DefaultGameController;
import game.GameController;

public class Client {

	public static void main(String[] args) {
		GameController controller = DefaultGameController.getInstance();
		controller.play();
	}

}
