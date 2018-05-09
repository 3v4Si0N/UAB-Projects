package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.BodyNode;
import game.Snake;
import game.Snake.Directions;

/**
 * Classe que prova la classe Snake.java
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class SnakeTest {
    
    private static final int DEFAULT_X_COORD = -15;
    private static final int DEFAULT_Y_COORD = -15;
    private static final int MIN_X_COORD = -15;
    private static final int MIN_Y_COORD = -15;
    private Snake snake;

    @Before
    public void setUp() throws Exception {
        this.snake = new Snake(DEFAULT_X_COORD, DEFAULT_Y_COORD);
    }

    /**
     * Test de constructor
     */
    @Test
    public void testSnake() {
        /*
         * Comprovem que 2 snake es creen iguals amb els mateixos paràmetres
         */
        Snake snake2 = new Snake(DEFAULT_X_COORD, DEFAULT_Y_COORD);
        
        assertEquals(this.snake.getPosX(), snake2.getPosX());
        assertEquals(this.snake.getPosX(), DEFAULT_X_COORD);
        assertEquals(this.snake.getPosY(), snake2.getPosY());
        assertEquals(this.snake.getPosY(), DEFAULT_Y_COORD);
        assertEquals(this.snake.getDirection(), snake2.getDirection());
        assertEquals(this.snake.getDirection(), Directions.UP);
        assertEquals(this.snake.isAlive(), snake2.isAlive());
        assertTrue(this.snake.isAlive());
        
        /*
         * Assegurem les posicions de snake, que si li donem posicions
         * de creacio incorrectes, agafa les de default.
         * També fem caixa blanca
         */
        this.snake = new Snake(MIN_X_COORD - 20, MIN_Y_COORD - 20);
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        int expectedX = -20;
        int expectedY = 20;
        this.snake = new Snake(expectedX, expectedY);
        assertEquals(MIN_X_COORD, this.snake.getPosX());
        assertEquals(MIN_Y_COORD, this.snake.getPosY());
        
        expectedX = 20;
        expectedY = -20;
        this.snake = new Snake(expectedX, expectedY);
        assertEquals(MIN_X_COORD, this.snake.getPosX());
        assertEquals(MIN_Y_COORD, this.snake.getPosY());
    }

    /**
     * Test de set alive
     * Nomes pot entrar true o false
     */
    @Test
    public void testSetAlive() {
        assertTrue(this.snake.isAlive());
        this.snake.setAlive(false);
        assertFalse(this.snake.isAlive());
        this.snake.setAlive(true);
        assertTrue(this.snake.isAlive());
    }

    /**
     * Test de is alive
     */
    @Test
    public void testIsAlive() {
        assertTrue(this.snake.isAlive());
        this.snake.setAlive(false);
        assertFalse(this.snake.isAlive());
    }

    /**
     * Test de set coordenada X
     * Comprova que la posicio estigui dins de la pantalla
     * sino, agafa posicions de default
     */
    @Test
    public void testSetPosX() {
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        this.snake.setPosX(MIN_X_COORD);
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        this.snake.setPosX(MIN_X_COORD - 20);
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        int expectedX = 100;
        this.snake.setPosX(expectedX);
        assertEquals(expectedX, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
    }

    /**
     * Test de get de la coordenada X
     */
    @Test
    public void testGetPosX() {
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        int expectedX = 100;
        this.snake.setPosX(expectedX);
        assertEquals(expectedX, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
    }

    /**
     * Test de set coordenada Y
     * Comprova que la posicio estigui dins de la pantalla
     * sino, agafa posicions de default
     */
    @Test
    public void testSetPosY() {
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        this.snake.setPosY(MIN_Y_COORD);
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        this.snake.setPosY(MIN_Y_COORD - 20);
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        int expectedY = 100;
        this.snake.setPosY(expectedY);
        assertEquals(expectedY, this.snake.getPosY());
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
    }

    /**
     * Test de get de la coordenada Y
     */
    @Test
    public void testGetPosY() {
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
        assertEquals(DEFAULT_Y_COORD, this.snake.getPosY());
        
        int expectedY = 100;
        this.snake.setPosY(expectedY);
        assertEquals(expectedY, this.snake.getPosY());
        assertEquals(DEFAULT_X_COORD, this.snake.getPosX());
    }

    /**
     * Comrpovem en aquest test case, que podem modificar la
     * direccio de la serp.
     * Comprovem tambe que no pot anar en el sentit oposat i que no pot ser null
     */
    @Test
    public void testSetDirection() {
        assertEquals(Directions.UP, this.snake.getDirection());
        
        this.snake.setDirection(null);
        assertEquals(Directions.UP, this.snake.getDirection());
        
        this.snake.setDirection(Directions.RIGHT);
        assertEquals(Directions.RIGHT, this.snake.getDirection());
        this.snake.setDirection(Directions.LEFT);
        assertEquals(Directions.RIGHT, this.snake.getDirection());
        
        this.snake.setDirection(Directions.DOWN);
        assertEquals(Directions.DOWN, this.snake.getDirection());
        this.snake.setDirection(Directions.UP);
        assertEquals(Directions.DOWN, this.snake.getDirection());
        
        this.snake.setDirection(Directions.LEFT);
        assertEquals(Directions.LEFT, this.snake.getDirection());
        this.snake.setDirection(Directions.RIGHT);
        assertEquals(Directions.LEFT, this.snake.getDirection());
        
        this.snake.setDirection(Directions.UP);
        assertEquals(Directions.UP, this.snake.getDirection());
        this.snake.setDirection(Directions.DOWN);
        assertEquals(Directions.UP, this.snake.getDirection());
    }

    /**
     * Test de get de la direccio de la serp i que retorna el nou valor donat
     */
    @Test
    public void testGetDirection() {
        assertEquals(Directions.UP, this.snake.getDirection());
        
        this.snake.setDirection(Directions.RIGHT);
        assertEquals(Directions.RIGHT, this.snake.getDirection());
    }

    /**
     * Test de get body i comprovem que al creixer, el cos de la serp
     * creix tambe en size
     */
    @Test
    public void testGetBody() {
        assertNotNull(this.snake.getBody());
        
        int expectedSize = 1;
        assertEquals(expectedSize, this.snake.getBody().size());
        
        this.snake.grow();
        expectedSize = 2;
        assertEquals(expectedSize, this.snake.getBody().size());
    }

    /**
     * Tests the snake's movement, first with one node, each position and after that
     * it grows 1 square and each position
     */
    @Test
    public void testMove() {
        // Check the starting snake state
        int initialX = 100;
        int initialY = 100;
        this.snake = new Snake(initialX, initialY);
        assertEquals(Directions.UP, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        
        // Move UP
        this.snake.move();
        assertEquals(Directions.UP, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY - BodyNode.getSize(), this.snake.getPosY());
        
        // Change direction and move DOWN
        this.snake.setDirection(Directions.LEFT);
        this.snake.setDirection(Directions.DOWN);
        assertEquals(Directions.DOWN, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY - BodyNode.getSize(), this.snake.getPosY());
        
        this.snake.move();
        assertEquals(Directions.DOWN, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        
        // Change direction and move RIGHT
        this.snake.setDirection(Directions.RIGHT);
        assertEquals(Directions.RIGHT, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        
        this.snake.move();
        assertEquals(Directions.RIGHT, this.snake.getDirection());
        assertEquals(initialX + BodyNode.getSize(), this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        
        // Change direction and move LEFT
        this.snake.setDirection(Directions.DOWN);
        this.snake.setDirection(Directions.LEFT);
        assertEquals(Directions.LEFT, this.snake.getDirection());
        assertEquals(initialX + BodyNode.getSize(), this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        
        this.snake.move();
        assertEquals(Directions.LEFT, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        
        // Increase body size
        int newBodySize = 2;
        this.snake.grow();
        assertEquals(Directions.LEFT, this.snake.getDirection());
        assertEquals(newBodySize, this.snake.getBody().size());
        
        // Move LEFT with body size 2
        this.snake.move();
        assertEquals(Directions.LEFT, this.snake.getDirection());
        assertEquals(initialX - BodyNode.getSize(), this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        assertEquals(initialX, this.snake.getBody().get(1).getPosX());
        assertEquals(initialY, this.snake.getBody().get(1).getPosY());
        
        // Change direction UP and move
        this.snake.setDirection(Directions.UP);
        this.snake.move();
        assertEquals(Directions.UP, this.snake.getDirection());
        assertEquals(initialX - BodyNode.getSize(), this.snake.getPosX());
        assertEquals(initialY - BodyNode.getSize(), this.snake.getPosY());
        assertEquals(initialX - BodyNode.getSize(), this.snake.getBody().get(1).getPosX());
        assertEquals(initialY, this.snake.getBody().get(1).getPosY());
        
        // Change direction RIGHT and move
        this.snake.setDirection(Directions.RIGHT);
        this.snake.move();
        assertEquals(Directions.RIGHT, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY - BodyNode.getSize(), this.snake.getPosY());
        assertEquals(initialX - BodyNode.getSize(), this.snake.getBody().get(1).getPosX());
        assertEquals(initialY - BodyNode.getSize(), this.snake.getBody().get(1).getPosY());
        
        // Change direction DOWN and move
        this.snake.setDirection(Directions.DOWN);
        this.snake.move();
        assertEquals(Directions.DOWN, this.snake.getDirection());
        assertEquals(initialX, this.snake.getPosX());
        assertEquals(initialY, this.snake.getPosY());
        assertEquals(initialX, this.snake.getBody().get(1).getPosX());
        assertEquals(initialY - BodyNode.getSize(), this.snake.getBody().get(1).getPosY());
    }

    /**
     * Test de creixer la serp i comprovar que els nodes nous
     * del cos creixen en el lloc correcte
     */
    @Test
    public void testGrow() {
        // Growing size
        int expectedSize = 1;
        assertEquals(expectedSize, this.snake.getBody().size());
        
        this.snake.grow();
        expectedSize = 2;
        assertEquals(expectedSize, this.snake.getBody().size());
        
        this.snake.grow();
        expectedSize = 3;
        assertEquals(expectedSize, this.snake.getBody().size());
        
        // Growing LEFT coordinates
        int expectedX = 100;
        int expectedY = 100;
        expectedSize = 1;
        this.snake = new Snake(expectedX, expectedY);
        this.snake.setDirection(Directions.LEFT);
        assertEquals(Directions.LEFT, this.snake.getDirection());
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        
        this.snake.grow();
        expectedSize = 2;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX + BodyNode.getSize(), this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(1).getPosY());
        
        this.snake.grow();
        expectedSize = 3;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX + BodyNode.getSize(), this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(1).getPosY());
        assertEquals(expectedX + BodyNode.getSize() + BodyNode.getSize(), this.snake.getBody().get(2).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(2).getPosY());
        
        // Growing DOWN coordinates
        expectedX = 100;
        expectedY = 100;
        expectedSize = 1;
        this.snake = new Snake(expectedX, expectedY);
        this.snake.setDirection(Directions.LEFT);
        this.snake.setDirection(Directions.DOWN);
        assertEquals(Directions.DOWN, this.snake.getDirection());
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        
        this.snake.grow();
        expectedSize = 2;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX, this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY - BodyNode.getSize(), this.snake.getBody().get(1).getPosY());
        
        this.snake.grow();
        expectedSize = 3;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX, this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY - BodyNode.getSize(), this.snake.getBody().get(1).getPosY());
        assertEquals(expectedX, this.snake.getBody().get(2).getPosX());
        assertEquals(expectedY - BodyNode.getSize() - BodyNode.getSize(), this.snake.getBody().get(2).getPosY());
        
        // Growing RIGHT coordinates
        expectedX = 100;
        expectedY = 100;
        expectedSize = 1;
        this.snake = new Snake(expectedX, expectedY);
        this.snake.setDirection(Directions.RIGHT);
        assertEquals(Directions.RIGHT, this.snake.getDirection());
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        
        this.snake.grow();
        expectedSize = 2;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX - BodyNode.getSize(), this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(1).getPosY());
        
        this.snake.grow();
        expectedSize = 3;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX - BodyNode.getSize(), this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(1).getPosY());
        assertEquals(expectedX - BodyNode.getSize() - BodyNode.getSize(), this.snake.getBody().get(2).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(2).getPosY());
        
        // Growing UP coordinates
        expectedX = 100;
        expectedY = 100;
        expectedSize = 1;
        this.snake = new Snake(expectedX, expectedY);
        this.snake.setDirection(Directions.UP);
        assertEquals(Directions.UP, this.snake.getDirection());
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        
        this.snake.grow();
        expectedSize = 2;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX, this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY + BodyNode.getSize(), this.snake.getBody().get(1).getPosY());
        
        this.snake.grow();
        expectedSize = 3;
        assertEquals(expectedSize, this.snake.getBody().size());
        assertEquals(expectedX, this.snake.getBody().get(0).getPosX());
        assertEquals(expectedY, this.snake.getBody().get(0).getPosY());
        assertEquals(expectedX, this.snake.getBody().get(1).getPosX());
        assertEquals(expectedY + BodyNode.getSize(), this.snake.getBody().get(1).getPosY());
        assertEquals(expectedX, this.snake.getBody().get(2).getPosX());
        assertEquals(expectedY + BodyNode.getSize() + BodyNode.getSize(), this.snake.getBody().get(2).getPosY());
    }

    /**
     * Snake no te area
     * ha de retornar null per defecte
     */
    @Test
    public void testGetArea() {
        assertNull(this.snake.getArea());
    }
}
