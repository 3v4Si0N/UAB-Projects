package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A bodyNode is a part of the snake's body.
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class BodyNode implements GameElement {
    
    /**
     * X coord of the node, top left of the area
     */
    private int posX;
    
    /**
     * Y coord of the node, top left of the area
     */
    private int posY;
    
    /**
     * Area of the node
     */
    private Rectangle area;
    
    /**
     * Each node is a sqare, size is its side length.
     * It's equal to the node movement
     */
    private static final int SIZE = 15;
    
    /**
     * Minimum X coord to be set
     */
    private static final int MIN_X_COORD = -15;
    
    /**
     * Minimum Y coord to be set
     */
    private static final int MIN_Y_COORD = -15;
    
    /**
     * Constructor
     * @param startingX initial X coord
     * @param startingY initial Y coord
     */
    public BodyNode (final int startingX, final int startingY) {
        this.posX = (startingX >= MIN_X_COORD ? startingX : MIN_X_COORD);
        this.posY = (startingY >= MIN_Y_COORD ? startingY : MIN_Y_COORD);
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
     * {@inheritDoc}
     * Also it sets the new bounds for the area
     */
    @Override
    public void setPosX(final int posX) {
        if (posX >= MIN_X_COORD) {
            this.posX = posX;
            this.area.setBounds(posX, this.posY, SIZE, SIZE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosY() {
        return this.posY;
    }

    /**
     * {@inheritDoc}
     * Also it sets the new bounds for the area
     */
    @Override
    public void setPosY(final int posY) {
        if (posY >= MIN_Y_COORD) {
            this.posY = posY;
            this.area.setBounds(this.posX, posY, SIZE, SIZE);
        }
    }
    
    /**
     * Gets the side length of the area
     * @return
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
    @Override
    public void drawElement(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(this.posX, this.posY, SIZE, SIZE);
    }
}
