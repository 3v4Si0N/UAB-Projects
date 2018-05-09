package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.Food;

/**
 * Tests per a la classe Food.java
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class FoodTest {

    /*
     * Variables a utilitzar durant els tests
     */
    private Food food;
    private static final int DEFAULT_X = 10;
    private static final int DEFAULT_Y = 10;
    
    /**
     * Setup de abans de cada test.
     */
    @Before
    public void setUp() {
        this.food = new Food(DEFAULT_X, DEFAULT_Y);
    }
    
    /**
     * Test de constructor.
     * Es comprova que 2 Food creats de la mateixa forma,
     * tenen les mateixes propietats entre ells i que tenen els mateixos valors
     * que atributs static de la classe Food.java
     */
    @Test
    public void testFood() {
        /**
         * Caixa negra i comprovació amb un altre objecte Food
         */
        int x = 10;
        int y = 10;
        Food food1 = new Food(x, y);
        Food food2 = new Food(x, y);
        
        assertEquals(food1.getPosX(), food2.getPosX());
        assertEquals(food1.getPosY(), food2.getPosY());
        assertEquals(food1.getSize(), food2.getSize());
        assertEquals(Food.getSize(), food1.getSize());
        assertEquals(food1.getArea(), food2.getArea());
        
        /**
         * Caixa blanca del constructor, passem per totes les linies
         * i comprovem tots els valors dels condicionals
         */
        int wrongX = -1;
        int wrongY = -1;
        int expectedX = 0;
        int expectedY = 0;
        food1 = new Food(wrongX, wrongY);
        assertEquals(expectedX, food1.getPosX());
        assertEquals(expectedY, food1.getPosY());
    }

    /**
     * Tests per obtenir la coordenada X
     */
    @Test
    public void testGetPosX() {
        /*
         * Assegurem que retorna un valor esperat
         */
        int expectedX = 10;
        assertEquals(expectedX, this.food.getPosX());
        
        /*
         * Si creem un Food amb un altre valor, el retorna, es a dir
         * no retorna un valor per defecte
         */
        expectedX = 20;
        Food food2 = new Food(expectedX, DEFAULT_Y);
        assertEquals(expectedX, food2.getPosX());
    }

    /**
     * Test de modificació de la coordenada X
     */
    @Test
    public void testSetPosX() {
        /*
         * Comprovem que esta retornant un valor correcte
         */
        int expectedX = 10;
        assertEquals(expectedX, this.food.getPosX());
        
        /*
         * Fiquem un nou valor de X i veiem
         * que Food no pot ser modificada la coordenada X
         */
        int newXCoord = 20;
        this.food.setPosX(newXCoord);
        assertEquals(expectedX, this.food.getPosX());
    }

    /**
     * Tests per obtenir la coordenada Y
     */
    @Test
    public void testGetPosY() {
        /*
         * Assegurem que retorna un valor esperat
         */
        int expectedY = 10;
        assertEquals(expectedY, this.food.getPosY());
        
        /*
         * Si creem un Food amb un altre valor, el retorna, es a dir
         * no retorna un valor per defecte
         */
        expectedY = 20;
        Food food2 = new Food(DEFAULT_X, expectedY);
        assertEquals(expectedY, food2.getPosY());
    }

    /**
     * Test de modificació de la coordenada Y
     */
    @Test
    public void testSetPosY() {
        /*
         * Comprovem que esta retornant un valor correcte
         */
        int expectedY = 10;
        assertEquals(expectedY, this.food.getPosY());
        
        /*
         * Fiquem un nou valor de Y i veiem
         * que Food no pot ser modificada la coordenada Y
         */
        int newYCoord = 20;
        this.food.setPosY(newYCoord);
        assertEquals(expectedY, this.food.getPosY());
    }

    /**
     * Test de comrpovacio del tamany de Food
     */
    @Test
    public void testGetSize() {
        /*
         * Comrpovem que retorna el valor esperat, tant
         * l'objecte, com el seu acces static
         */
        int expectedSize = 15;
        assertEquals(expectedSize, this.food.getSize());
        assertEquals(expectedSize, Food.getSize());
    }

    /**
     * Test de Area
     */
    @Test
    public void testGetArea() {
        /*
         * Comrpovem el tamany de l'area de food
         */
        assertEquals(Food.getSize(), (int) this.food.getArea().getWidth());
        assertEquals(Food.getSize(), (int) this.food.getArea().getHeight());
        
        /*
         * Comrpovem valors entre dos objectes Food
         */
        Food food2 = new Food(DEFAULT_X, DEFAULT_Y);
        assertEquals((int) this.food.getArea().getWidth(), (int) food2.getArea().getWidth());
        assertEquals((int) this.food.getArea().getHeight(), (int) food2.getArea().getWidth());
        assertEquals((int) this.food.getArea().getCenterX(), (int) food2.getArea().getCenterX());
        assertEquals((int) this.food.getArea().getCenterY(), (int) food2.getArea().getCenterY());
    }
}
