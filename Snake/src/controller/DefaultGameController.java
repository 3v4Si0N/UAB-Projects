package controller;

import game.DefaultScreen;
import game.Snake;

/**
 * Implementation of the game controller interface
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class DefaultGameController implements GameController {
	
	private static DefaultScreen screen;
	private static Snake snake;
	
    public static final int SNAKE_STARTING_X = 315;
    public static final int SNAKE_STARTING_Y = 315;
	
    /**
     * Contructor.
     * Creates the snake and the screen
     */
	public DefaultGameController() {
		snake = new Snake(SNAKE_STARTING_X, SNAKE_STARTING_Y);
    	screen = new DefaultScreen(snake);
    }
    
	/**
	 * Plays the game by starting the screen
	 */
	@Override
    public void play() {
    	Thread thread = new Thread(screen);
    	thread.start();
    }
}
