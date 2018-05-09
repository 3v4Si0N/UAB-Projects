package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.DefaultGameController;
import game.Snake.Directions;

public class KeyEvents implements KeyListener {
	
	private Snake snake;
	
	public KeyEvents(final Snake newSnake) {
	    if (newSnake != null) {
	        this.snake = newSnake;
	    } else {
	        this.snake = new Snake(
	                DefaultGameController.SNAKE_STARTING_X,
	                DefaultGameController.SNAKE_STARTING_Y);
	    }
	}
	
	public Snake getSnake() {
	    return this.snake;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();

		switch (key) {
    		case KeyEvent.VK_LEFT:
    		    if (snake.getDirection() != Directions.RIGHT) {
    		        this.snake.setDirection(Directions.LEFT);
    		    }
    		    break;
    		case KeyEvent.VK_RIGHT:
    		    if (snake.getDirection() != Directions.LEFT) {
    		        this.snake.setDirection(Directions.RIGHT);
    		    }
                break;
    		case KeyEvent.VK_DOWN:
    		    if (snake.getDirection() != Directions.UP) {
    		        this.snake.setDirection(Directions.DOWN);
    		    }
                break;
    		case KeyEvent.VK_UP:
    		    if (snake.getDirection() != Directions.DOWN) {
    		        this.snake.setDirection(Directions.UP);
                }
                break;
    		default:
    		    break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void keyTyped(KeyEvent event) {}
}
