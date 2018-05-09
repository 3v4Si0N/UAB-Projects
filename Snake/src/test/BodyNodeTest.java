package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.BodyNode;

/**
 * Classe que prova els nodes del cos de la serp
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class BodyNodeTest {

    private BodyNode bodyNode;
    private static final int DEFAULT_X_COORD = 10;
    private static final int DEFAULT_Y_COORD = 10;
    private static final int MIN_X_COORD = -15;
    private static final int MIN_Y_COORD = -15;
    
    @Before
    public void setUp() throws Exception {
        this.bodyNode = new BodyNode(DEFAULT_X_COORD, DEFAULT_Y_COORD);
    }

    /**
     * Creació de nodes
     */
    @Test
    public void testBodyNode() {
        /*
         * Es creen 2 nodes i es comprova que s'han creat de la mateixa forma
         */
        BodyNode bodyNode2 = new BodyNode(DEFAULT_X_COORD, DEFAULT_Y_COORD);
        
        assertEquals(this.bodyNode.getPosX(), bodyNode2.getPosX());
        assertEquals(this.bodyNode.getPosY(), bodyNode2.getPosY());
        assertEquals(this.bodyNode.getSize(), bodyNode2.getSize());
        assertEquals(BodyNode.getSize(), this.bodyNode.getSize());
        assertEquals(this.bodyNode.getArea(), bodyNode2.getArea());
        
        /*
         * s'intenta possar coordenades no vàlides del node
         * per tal de cobrir un 100% de coverage de les
         * condicions de creació
         */
        bodyNode2 = new BodyNode(MIN_X_COORD - 15, MIN_Y_COORD - 15);
        assertEquals(MIN_X_COORD, bodyNode2.getPosX());
        assertEquals(MIN_Y_COORD, bodyNode2.getPosY());
    }

    /**
     * Test d'obtenció de la coordenada X
     */
    @Test
    public void testGetPosX() {
        int expectedX = DEFAULT_X_COORD;
        
        assertEquals(expectedX, this.bodyNode.getPosX());
        
        /*
         * Es modifica la coordenada i 
         * es comprova que dona el resultat
         * esperat
         */
        expectedX = 20;
        this.bodyNode.setPosX(expectedX);
        assertEquals(expectedX, this.bodyNode.getPosX());
    }

    /**
     * Comrpovació d'actualització de la coordenada X
     * i de l'àrea del node
     */
    @Test
    public void testSetPosX() {
        /*
         * Es dona diferents valors de X, tant per caixa negra
         * com per complir amb tests de caixa blanca
         */
        int expectedX = 20;
        
        assertEquals(DEFAULT_X_COORD, this.bodyNode.getPosX());
        
        this.bodyNode.setPosX(expectedX);
        assertEquals(expectedX, this.bodyNode.getPosX());
        assertEquals(expectedX, (int) this.bodyNode.getArea().getX());
        
        this.bodyNode.setPosX(MIN_X_COORD);
        assertEquals(MIN_X_COORD, this.bodyNode.getPosX());
        assertEquals(MIN_X_COORD, (int) this.bodyNode.getArea().getX());
        
        expectedX = MIN_X_COORD - 15;
        this.bodyNode.setPosX(expectedX);
        assertEquals(MIN_X_COORD, this.bodyNode.getPosX());
        assertEquals(MIN_X_COORD, (int) this.bodyNode.getArea().getX());
    }

    /**
     * Test d'obtenció de la coordenada Y
     */
    @Test
    public void testGetPosY() {
        int expectedY = DEFAULT_Y_COORD;
        
        assertEquals(expectedY, this.bodyNode.getPosY());
        
        /*
         * Es modifica la coordenada i 
         * es comprova que dona el resultat
         * esperat
         */
        expectedY = 20;
        this.bodyNode.setPosY(expectedY);
        assertEquals(expectedY, this.bodyNode.getPosY());
    }

    /**
     * Comrpovació d'actualització de la coordenada Y
     * i de l'àrea del node
     */
    @Test
    public void testSetPosY() {
        /*
         * Es dona diferents valors de Y, tant per caixa negra
         * com per complir amb tests de caixa blanca
         */
        int expectedY = 20;
        
        assertEquals(DEFAULT_Y_COORD, this.bodyNode.getPosY());
        
        this.bodyNode.setPosY(expectedY);
        assertEquals(expectedY, this.bodyNode.getPosY());
        assertEquals(expectedY, (int) this.bodyNode.getArea().getY());
        
        this.bodyNode.setPosY(MIN_Y_COORD);
        assertEquals(MIN_Y_COORD, this.bodyNode.getPosY());
        assertEquals(MIN_Y_COORD, (int) this.bodyNode.getArea().getY());
        
        expectedY = MIN_Y_COORD - 15;
        this.bodyNode.setPosY(expectedY);
        assertEquals(MIN_Y_COORD, this.bodyNode.getPosY());
        assertEquals(MIN_Y_COORD, (int) this.bodyNode.getArea().getY());
    }

    /**
     * Comprova que el size d'un node sempre es el mateix
     * tant amb altres nodes, com amb el static
     */
    @Test
    public void testGetSize() {
        BodyNode bodyNode2 = new BodyNode(DEFAULT_X_COORD, DEFAULT_Y_COORD);

        assertEquals(this.bodyNode.getSize(), bodyNode2.getSize());
        assertEquals(this.bodyNode.getSize(), BodyNode.getSize());
        assertEquals(bodyNode2.getSize(), BodyNode.getSize());
    }

    /**
     * Comprova que les àrees de 2 nodes son les mateixes si es creen
     * els nodes de forma igual
     */
    @Test
    public void testGetArea() {
        assertEquals(BodyNode.getSize(), (int) this.bodyNode.getArea().getWidth());
        assertEquals(BodyNode.getSize(), (int) this.bodyNode.getArea().getHeight());
        
        BodyNode bodyNode2 = new BodyNode(DEFAULT_X_COORD, DEFAULT_Y_COORD);
        
        assertEquals((int) this.bodyNode.getArea().getWidth(), (int) bodyNode2.getArea().getWidth());
        assertEquals((int) this.bodyNode.getArea().getHeight(), (int) bodyNode2.getArea().getWidth());
        assertEquals((int) this.bodyNode.getArea().getCenterX(), (int) bodyNode2.getArea().getCenterX());
        assertEquals((int) this.bodyNode.getArea().getCenterY(), (int) bodyNode2.getArea().getCenterY());
    }
}
