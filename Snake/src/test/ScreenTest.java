package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import game.DefaultScreen;
import game.Food;
import game.MockDefaultScreen;
import game.Snake;
import game.Snake.Directions;

public class ScreenTest {
	
	private DefaultScreen screen;
	private Snake snake;
	
	private static int GAME_WIDTH = 630;
	private static int GAME_HEIGHT = 600;
    private static final int DEFAULT_X_SNAKE = 20;
    private static final int DEFAULT_Y_SNAKE = 20;

    @Before
    public void setUp() throws Exception {
        this.snake = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
        this.screen = new DefaultScreen(snake);
    }
	
    /**
     * Se comprueba que una coordenada para el elemento Food se genera 100 veces dentro de 
     * la pantalla y nunca fuera de ella. Si al generarse 100 veces, ninguna de ellas se crea
     * fuera de la pantalla, SUPONEMOS que se ha generado correctamete. No podemos
     * asegurar al 100% que nunca se vaya a generar fuera, solo podemos decir que la
     * probabilidad es mínima.
     */
	@Test
	public void testGenerateFoodRandomCoordinate() {
		boolean result = false;
		
		for (int i = 0; i < 100; i++) {
			screen.createFood();
			Food food = this.screen.getFood();
			if (food.getPosX() <= GAME_WIDTH && food.getPosY() <= GAME_HEIGHT) {
				result = true;
			}
			
			assertTrue(result);
		}
	}

	
	/**
	 * Se comprueba que la comida siempre se genera y que en ningún
	 * caso la comida se genera en la misma posición en la que se
	 * encuentra la serpierte.
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testCreateFood() {
		Food food = null;
		int expected = 15;
		
		for (int i = 0; i < 100; i++) {
			boolean result = true;
			screen.createFood();
			food = screen.getFood();
			if (snake.getPosX() == food.getPosX() && snake.getPosY() == food.getPosY()) {
				result = false;
			}
			assertEquals(expected, food.getSize());
			assertTrue(result);
		}
	}
	
	
	/**
	 * Se compruieba que la comida nunca es null,
	 * es decir, siempre hay una comida en el tablero,
	 * desde que la serpiente se come una, se vuelve a 
	 * crear una nueva.
	 */
	@Test
	public void testGetFood() {
		Food food = screen.getFood();
		
		assertEquals(15, Food.getSize());
		assertNotNull(food);
	}
	

	/**
	 * Se comprueba que la colisión entre la serpiente y la comida
	 * se realiza correctamente. Se realizan distintos test, uno
	 * creando la comida en la esquina superior izquierda, en
	 * la esquina superior derecha, en la esquina inferior izquierda,
	 * en la esquina inferior derecha y una en la posiciçón 200,200
	 * en medio de la pantalla. Para comprobar la colisión entre la serpiente
	 * y la comida, se crea la comida en el punto que queremos, insertamos la
	 * serpiente unos cuantos píxeles alejada de la comida y movemos la serpiente
	 * hacia la comida. El método eatFood comprobará si los dos rectángulos han
	 * interceptado y si eso pasa, devolverá true.
	 */
	@Test
	public void testEatFood() {
		
		Food food = this.screen.getFood();
		this.snake.setPosX(food.getPosX());
		this.snake.setPosY(food.getPosY());
		assertTrue(this.screen.eatFood());
		
		this.screen.createFood();
		food = this.screen.getFood();
		this.snake.setPosX(0);
		this.snake.setPosY(0);
		while (this.snake.getPosX() == food.getPosX() && this.snake.getPosY() == food.getPosY()) {
			food = this.screen.getFood();
		}
		assertFalse(this.screen.eatFood());
		
		MockDefaultScreen mockScreen = new MockDefaultScreen(this.snake);
		for (int i = 0; i < 5; i++) {
			mockScreen.createFood();
			food = mockScreen.getFood();
			
			// Upper left corner
			if (food.getPosX() == 0 && food.getPosY() == 0) {
				this.snake.setPosX(food.getPosX());
				this.snake.setPosY(food.getPosY() + 20);
				this.snake.setDirection(Directions.UP);
				this.snake.move();
			} 
			// Upper right corner
			else if (food.getPosX() == GAME_WIDTH && food.getPosY() == 0) {
				this.snake.setPosX(food.getPosX()+10);
				this.snake.setPosY(food.getPosY());
				this.snake.setDirection(Directions.RIGHT);
				this.snake.move();
			}
			// Lower left corner
			else if (food.getPosX() == 0 && food.getPosY() == GAME_HEIGHT) {
				this.snake.setPosX(food.getPosX() + 10);
				this.snake.setPosY(food.getPosY());
				this.snake.setDirection(Directions.LEFT);
				this.snake.move();
			}
			// Lower right corner
			else if(food.getPosX() == GAME_WIDTH && food.getPosY() == GAME_HEIGHT) {
				this.snake.setPosX(food.getPosX());
				this.snake.setPosY(food.getPosY() - 10);
				this.snake.setDirection(Directions.DOWN);
				this.snake.move();
			}
			// Another random pos on screen
			else {
				this.snake.setPosX(food.getPosX() + 10);
				this.snake.setPosY(food.getPosY());
				this.snake.setDirection(Directions.LEFT);
				this.snake.move();
			}
			assertTrue(mockScreen.eatFood());
		}
	}
	
	
	/**
	 * Se comprueba si al llamar al método increaseDificulty(),
	 * éste aumenta la dificultad y los puntos.
	 * También se comprueba que la dificultad no sea menor a 0,
	 * ya que la dificultad se controla con un Thread.sleep() y 
	 * éste no puede ser inferior a 0.
	 */
	@Test
	public void testIncreaseDificulty() {
	    int initialGameSpeed = 150;
	    int initialPoints = 0;
	    
	    assertEquals(initialGameSpeed, this.screen.getGameSpeed());
	    assertEquals(initialPoints, this.screen.getScore());
	    
	    this.screen.increaseDificulty();
	    
	    assertEquals(initialGameSpeed - 1, this.screen.getGameSpeed());
        assertEquals(initialPoints + 10, this.screen.getScore());
        
        this.screen.increaseDificulty();
        
        assertEquals(initialGameSpeed - 2, this.screen.getGameSpeed());
        assertEquals(initialPoints + 20, this.screen.getScore());
        
        // Limit case when speed is lees than 0
        for (int i = initialGameSpeed; i >= -1; i--) {
        	this.screen.increaseDificulty();
        	if (i < 0) {
        		assertEquals(0, this.screen.getGameSpeed());
        	}
        }
	}
	
	
	/**
	 * Se comprueba que la velocidad del juego es la que corresponde.
	 */
	@Test
	public void testGetGameSpeed() {
		int initialGameSpeed = 150;
		
		assertNotNull(this.screen.getGameSpeed());
		assertEquals(initialGameSpeed, this.screen.getGameSpeed());
		
		this.screen.increaseDificulty();
		assertEquals(initialGameSpeed - 1, this.screen.getGameSpeed());
		
		this.screen.increaseDificulty();
		assertNotEquals(initialGameSpeed - 1, this.screen.getGameSpeed());
	}
	
	
	/**
	 * Se comprueba que la puntuación es la que corresponde.
	 */
	@Test
	public void testGetScore() {
		int initialScore = 0;
		
		assertNotNull(this.screen.getScore());
		assertEquals(initialScore, this.screen.getScore());
		
		this.screen.increaseDificulty();
		assertEquals(initialScore + 10, this.screen.getScore());
		
		this.screen.increaseDificulty();
		assertNotEquals(initialScore + 19, this.screen.getScore());
	}
	
	
	/**
	 * Se comprueba que la serpiente al salir por una pared, aparezca por la pared inversa.
	 * Para esto se realiza 4 test case, uno para cada pared y un quinto test
	 * para realizar un decision coverage y los tests cubran todas las posibilidades.
	 */
	@Test
	public void testSnakeWallCollision() {
		
		// Left wall
		int expectedPosX = 630;
		for (int i = 0; i <= 1; i++) {
			this.snake.setDirection(Directions.LEFT);
			this.snake.move();
		}
		this.screen.snakeWallCollision();
		assertEquals(expectedPosX, this.snake.getBody().get(0).getPosX());
		
		// Right wall
		expectedPosX = 0;
		Snake snake2 = new Snake(DEFAULT_X_SNAKE+620, DEFAULT_Y_SNAKE);
		DefaultScreen screen2 = new DefaultScreen(snake2);
		for (int i = 0; i <= 1; i++) {
			snake2.setDirection(Directions.RIGHT);
			snake2.move();
		}
		screen2.snakeWallCollision();
		assertEquals(expectedPosX, snake2.getBody().get(0).getPosX());
		
		//Upper wall
		int expectedPosY = 600;
		Snake snake3 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
		DefaultScreen screen3 = new DefaultScreen(snake3);
		for (int i = 0; i <= 1; i++) {
			snake3.setDirection(Directions.UP);
			snake3.move();
		}
		screen3.snakeWallCollision();
		assertEquals(expectedPosY, snake3.getBody().get(0).getPosY());
		
		//Lower wall
		expectedPosY = 0;
		Snake snake4 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE+600);
		DefaultScreen screen4 = new DefaultScreen(snake4);
		for (int i = 0; i <= 1; i++) {
			snake4.setDirection(Directions.RIGHT);
			snake4.move();
			snake4.setDirection(Directions.DOWN);
			snake4.move();
		}
		screen4.snakeWallCollision();
		assertEquals(expectedPosY, snake4.getBody().get(0).getPosY());
		
		// not wall
		expectedPosX = 35;
		Snake snake5 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
		DefaultScreen screen5 = new DefaultScreen(snake5);
		snake5.setDirection(Directions.RIGHT);
		snake5.move();
		screen5.snakeWallCollision();
		assertEquals(expectedPosX, snake5.getBody().get(0).getPosX());
	}
	
	/**
	 * Se comprueba que la cabeza de la serpiente al chocar con
	 * un nodo de su cuerpo, muera. Se realizan distintos test cases
	 * para comprobar que no importa la dirección que lleve la serpiente,
	 * si la cabeza de la serpiente intercepta con un nodo del cuerpo, muere.
	 * También se realiza un último test en el que la serpiente no choca consigo
	 * misma, para comprobar que efectivamente si la cabeza de la serpiente no intercepta
	 * con ningún nodo, ésta no muere.
	 */
	@Test
	public void testSnakeBodyCollision() {
		
		// Collision 1
		for (int i = 0; i <= 4; i++) {
			this.snake.grow();
		}

		this.snake.setDirection(Directions.LEFT);
		this.snake.move();
		this.snake.setDirection(Directions.DOWN);
		this.snake.move();
		this.snake.setDirection(Directions.RIGHT);
		this.snake.move();
		assertTrue(this.screen.snakeBodyCollision());
		
		// Collision 2
		Snake snake2 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
		DefaultScreen screen2 = new DefaultScreen(snake2);
		for (int i = 0; i <= 4; i++) {
			snake2.grow();
		}

		snake2.setDirection(Directions.RIGHT);
		snake2.move();
		snake2.setDirection(Directions.DOWN);
		snake2.move();
		snake2.setDirection(Directions.LEFT);
		snake2.move();
		assertTrue(screen2.snakeBodyCollision());
		
		// Collision 3
		Snake snake3 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
		DefaultScreen screen3 = new DefaultScreen(snake3);
		for (int i = 0; i <= 4; i++) {
			snake3.grow();
		}

		snake3.setDirection(Directions.LEFT);
		snake3.move();
		snake3.setDirection(Directions.DOWN);
		snake3.move();
		snake3.setDirection(Directions.LEFT);
		snake3.move();
		assertFalse(screen3.snakeBodyCollision());
		
		// Collision 4
		Snake snake4 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
		DefaultScreen screen4 = new DefaultScreen(snake4);
		for (int i = 0; i <= 3; i++) {
			snake4.grow();
		}

		snake4.setDirection(Directions.RIGHT);
		snake4.move();
		snake4.setDirection(Directions.DOWN);
		snake4.move();
		snake4.setDirection(Directions.RIGHT);
		snake4.move();
		assertFalse(screen4.snakeBodyCollision());
		
		// Not collision
		Snake snake5 = new Snake(DEFAULT_X_SNAKE, DEFAULT_Y_SNAKE);
		DefaultScreen screen5 = new DefaultScreen(snake5);
		for (int i = 0; i <= 2; i++) {
			snake5.grow();
		}

		snake5.setDirection(Directions.RIGHT);
		snake5.move();
		snake5.setDirection(Directions.DOWN);
		snake5.move();
		snake5.setDirection(Directions.LEFT);
		snake5.move();
		snake5.setDirection(Directions.UP);
		snake5.move();
		assertFalse(screen5.snakeBodyCollision());
	}
}
