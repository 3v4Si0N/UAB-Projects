package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Default screen for the game
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
@SuppressWarnings("serial")
public class DefaultScreen extends JPanel implements Runnable {
	
    /**
     * Screen default sizes
     */
	private static final int DEFAULT_WIDTH = 630;
    private static final int DEFAULT_HEIGHT = 680;
    
    /**
     * Default sizes where the game will run
     */
	private static final int GAME_WIDTH = 630;
    private static final int GAME_HEIGHT = 600;
    
    /**
     * Screen title and definition of the frame
     */
    private static final String SCREEN_TITLE = "Snake";
    private JFrame screen = new JFrame(SCREEN_TITLE);
    
    /**
     * Snake to play with
     */
	private static Snake snake;
	
	/**
	 * Food to be eaten
	 */
	private static Food food;
	
	/**
	 * Game speed, the less, the faster
	 */
	private static final int STARTING_GAME_SPEED = 150;
	private static int gameSpeed;
	
	/**
	 * Game points and initial coordinates to be shown
	 */
	private static int points;
	private static int POINTS_X = 20;
	private static int POINTS_Y = 630;
	
	/**
	 * Key events
	 */
	private KeyEvents keyEvents;

	/**
	 * Constructor.
	 * Starts everytinh at default positions
	 * @param newSnake snake to play with
	 */
	public DefaultScreen(Snake newSnake) {
		snake = newSnake;
		this.keyEvents = new KeyEvents(snake);
	    points = 0;
	    this.createFood();
	    gameSpeed = STARTING_GAME_SPEED;
	    
		screen.addKeyListener(keyEvents);
		screen.add(this); // Add this JPanel to JFrame
		screen.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setBackground(Color.BLACK);
		screen.setVisible(true);
	}
	
	/**
	 * Generates a random number to be set for the food coordinates
	 * @return int within the frame limits
	 */
	public int generateFoodRandomCoordinate() {
	    return (new Random(System.currentTimeMillis())
	            .nextInt(GAME_WIDTH - 3 * Food.getSize()) / Food.getSize()
	            ) * Food.getSize(); 
	}
	
	/**
	 * Creates a food outside the snake head,
	 * in order not to eat two foods at the same time
	 */
	public void createFood() {
	    int posX = this.generateFoodRandomCoordinate();
        int posY = this.generateFoodRandomCoordinate();
	    
        while (posX == snake.getPosX() && posY == snake.getPosY()) {
	        posX = this.generateFoodRandomCoordinate();
	        posY = this.generateFoodRandomCoordinate();
	    }
	    food = new Food(posX, posY);
	}
	
	/**
	 * Getter of food
	 * @return screen food
	 */
	public Food getFood() {
		return food;
	}
	
	/**
	 * Paints the snake, food, and the score
	 */
	@Override
	public void paint(Graphics g) {
	    snake.drawElement(g);
		food.drawElement(g);
		this.drawScore(g);
	}
	
	/**
	 * Check that the snake head intersect the food.
	 * If that happens, the snake grows, another food is created and
	 * the game difficulty is increased
	 * @return true if the food is eaten, false otherwise
	 */
	public boolean eatFood() {
		boolean result = false;
		
	    if (snake.getBody().get(0).getArea().intersects(food.getArea())) {
	        snake.grow();
	        this.createFood();
	        this.increaseDificulty();
	        result = true;
	    }
	    return result;
	}
	
	/**
	 * Increases difficulty of the game by deacreasing the gamespeed,
	 * which is the sleep time of the game, and increasing the points.
	 */
	public void increaseDificulty() {
		if (gameSpeed > 0) {
			gameSpeed -= 1;
		}
	    points += 10;
	}
	
	/**
     * Getter of speed
     * @return current game speed
     */
	public int getGameSpeed() {
		return gameSpeed;
	}
	
	/**
	 * Getter of points
	 * @return current points
	 */
	public int getScore() {
		return points;
	}
	
	/**
	 * Checks movement with the walls, if that happens,
	 * the first snake node receives a new coordinate
	 */
	public void snakeWallCollision() {
	    if (snake.getPosX() < 0) {
	        snake.setPosX(GAME_WIDTH);
	    } else if (snake.getPosX() > GAME_WIDTH) {
	        snake.setPosX(0);
	    } else if (snake.getPosY() < 0) {
            snake.setPosY(GAME_HEIGHT);
        } else if (snake.getPosY() > GAME_HEIGHT) {
            snake.setPosY(0);
        }
	}
	
	/**
	 * Checks the snake does not eat itself.
	 * That can only happen when the size is bigger than 5 nodes.
	 * If the snake eats itself, the game ends.
	 */

	public boolean snakeBodyCollision() {
		boolean collision = false;
		if (snake.getBody().size() >= 5) {
		    for (int i = 4; i < snake.getBody().size(); i++) {
	            if (snake.getBody().get(0).getArea().intersects(snake.getBody().get(i).getArea())) {
	                snake.setAlive(false);
	                collision = true;
	            }
	        }
		}
		return collision;
	}

	/**
	 * Runs the game
	 */
	@Override
	public void run() {
		while (snake.isAlive()) {
			try {
                Thread.sleep(gameSpeed);
                snake.move();
                screen.repaint();
                eatFood();
                this.snakeWallCollision();
                if (this.snakeBodyCollision()) {
                	JOptionPane.showMessageDialog(null, "Snake died!\n You got: " + points + " points");
                	System.exit(0);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }	
		}
	}
	
	/**
	 * Draws the score
	 * @param g graphic
	 */
	private void drawScore(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.fillRect(0, POINTS_Y - 15, DEFAULT_WIDTH, 20);
		
		g2.setColor(Color.WHITE);
		g2.drawString("SCORE: " + points, POINTS_X, POINTS_Y);
	}
}
