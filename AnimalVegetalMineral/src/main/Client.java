package main;

import controller.GameController;

/**
 * Class client to run the game
 * 
 * @author Ramon Guimer�, H�ctor De Armas
 *
 */
public class Client {

    /**
     * MAIN
     * @param args it does not take any argument
     */
    public static void main(String[] args) {
        GameController.getInstance().play();
    }
}
