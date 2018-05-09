package test;

import static org.junit.Assert.*;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import org.junit.Before;
import org.junit.Test;
import controller.DefaultGameController;
import game.KeyEvents;
import game.Snake;
import game.Snake.Directions;

/**
 * Classe de prova de KeyEvents
 * Utilitza un component com a mockobject
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class KeyEventsTest {

    JTextField component; // mock object component
    KeyEvent leftKey;
    KeyEvent rightKey;
    KeyEvent upKey;
    KeyEvent downKey;
    KeyEvent otherKey;
    
    @Before
    public void setUp() throws Exception {
        this.component = new JTextField();
        
        this.leftKey = new KeyEvent(
                component,
                1,
                System.currentTimeMillis(),
                1,
                KeyEvent.VK_LEFT,
                (char) KeyEvent.VK_LEFT);
        
        this.rightKey = new KeyEvent(
                component,
                1,
                System.currentTimeMillis(),
                1,
                KeyEvent.VK_RIGHT,
                (char) KeyEvent.VK_RIGHT);
        
        this.upKey = new KeyEvent(
                component,
                1,
                System.currentTimeMillis(),
                1,
                KeyEvent.VK_UP,
                (char) KeyEvent.VK_UP);
        
        this.downKey = new KeyEvent(
                component,
                1,
                System.currentTimeMillis(),
                1,
                KeyEvent.VK_DOWN,
                (char) KeyEvent.VK_DOWN);
        
        this.otherKey = new KeyEvent(
                component,
                1,
                System.currentTimeMillis(),
                1,
                KeyEvent.VK_1,
                (char) KeyEvent.VK_1);
    }
    
    /**
     * Provem tecles premades de tal forma que la serp
     * cavii de direccií i complir els condition coverage
     * dels tests de caixa blanca
     */
    @Test
    public void testKeyPressed() {
        int posX = 100;
        int posY = 100;
        
        Snake snake = new Snake(posX, posY);
        KeyEvents keyEvents = new KeyEvents(snake);
        
        assertEquals(Directions.UP, snake.getDirection());
        keyEvents.keyPressed(this.downKey);
        assertEquals(Directions.UP, snake.getDirection());
        
        keyEvents.keyPressed(this.leftKey);
        assertEquals(Directions.LEFT, snake.getDirection());
        keyEvents.keyPressed(this.rightKey);
        assertEquals(Directions.LEFT, snake.getDirection());
        
        keyEvents.keyPressed(this.downKey);
        assertEquals(Directions.DOWN, snake.getDirection());
        keyEvents.keyPressed(this.upKey);
        assertEquals(Directions.DOWN, snake.getDirection());
        
        keyEvents.keyPressed(this.rightKey);
        assertEquals(Directions.RIGHT, snake.getDirection());
        keyEvents.keyPressed(this.leftKey);
        assertEquals(Directions.RIGHT, snake.getDirection());
        
        keyEvents.keyPressed(this.upKey);
        assertEquals(Directions.UP, snake.getDirection());
        keyEvents.keyPressed(this.downKey);
        assertEquals(Directions.UP, snake.getDirection());
        
        keyEvents.keyPressed(this.otherKey);
        assertEquals(Directions.UP, snake.getDirection());
    }
    
    /**
     * Prova del constructor de keyevents, de tal forma
     * que no doni valors no corresponents
     */
    @Test
    public void testKeyEvents()  {
        Snake snake = null;
        KeyEvents keyEvents = new KeyEvents(snake);
        
        assertNotNull(keyEvents.getSnake());
        assertEquals(DefaultGameController.SNAKE_STARTING_X, keyEvents.getSnake().getPosX());
        assertEquals(DefaultGameController.SNAKE_STARTING_Y, keyEvents.getSnake().getPosY());
        
        int posX = 100;
        int posY = 100;
        
        snake = new Snake(posX, posY);
        keyEvents = new KeyEvents(snake);
        assertNotNull(keyEvents.getSnake());
        assertEquals(posX, keyEvents.getSnake().getPosX());
        assertEquals(posY, keyEvents.getSnake().getPosY());
    }
    
    /**
     * Prova de keyreleased
     * Es prova de que no afecti al joc, doncs, no fa res
     */
    @Test
    public void testKeyReleased() {
        int posX = 100;
        int posY = 100;
        
        Snake snake = new Snake(posX, posY);
        KeyEvents keyEvents = new KeyEvents(snake);
        
        assertEquals(Directions.UP, snake.getDirection());
        keyEvents.keyReleased(this.downKey);
        assertEquals(Directions.UP, snake.getDirection());
    }
    
    /**
     * Prova de keytyped
     * Es prova de que no afecti al joc, doncs, no fa res
     */
    @Test
    public void testKeyTyped() {
        int posX = 100;
        int posY = 100;
        
        Snake snake = new Snake(posX, posY);
        KeyEvents keyEvents = new KeyEvents(snake);
        
        assertEquals(Directions.UP, snake.getDirection());
        keyEvents.keyTyped(this.downKey);
        assertEquals(Directions.UP, snake.getDirection());
    }
}
