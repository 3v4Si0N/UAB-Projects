package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Food is what the snake eats.
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class Food implements GameElement {
    
    /**
     * X coord
     */
    private int posX;
    
    /**
     * Y coord
     */
    private int posY;
    
    /**
     * Rectangle of the food, figure to draw
     */
    private Rectangle area;

    /**
     * Const size of the area
     */
    private static final int SIZE = 15;
    
    /**
     * Default X coord if the given in the constructor is not correct
     */
    private static final int DEFAULT_X_COORD = 0;
    
    /**
     * Default Y coord if the given in the constructor is not correct
     */
    private static final int DEFAULT_Y_COORD = 0;
    
    /**
     * Constructor
     * @param startingX x coordinate, top left of the area
     * @param startingY y coordinate, top left of the area
     */
    public Food(final int startingX, final int startingY) {
        this.posX = (startingX >= DEFAULT_X_COORD ? startingX : DEFAULT_X_COORD);
        this.posY = (startingY >= DEFAULT_Y_COORD ? startingY : DEFAULT_Y_COORD);
        this.area = new Rectangle(startingX, startingY, SIZE, SIZE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosX() {
        return this.posX;
    }
    
    /**
     * Does nothing, X coord should not be changed
     */
    @Override
    public void setPosX(final int newPosX) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosY() {
        return this.posY;
    }

    /**
     * Does nothing, Y coord should not be changed
     */
    @Override
    public void setPosY(final int newPosY) { }
    
    /**
     * Gets size
     * @return size of food
     */
    public static int getSize() {
        return SIZE;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getArea() {
        return this.area;
    }
    
    /**
     * {@inheritDoc}
     */
    public void drawElement(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(this.posX, this.posY, SIZE, SIZE);
    }
}
