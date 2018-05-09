package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Every element in the game must implement this interface
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public interface GameElement {
	
    /**
     * Sets X coordinate for the game element
     * @param newPosX new X coord to set
     */
	public void setPosX(int newPosX);
	
	/**
	 * Gets X coord of the element
	 * @return X coord
	 */
	public int getPosX();
	
	/**
     * Sets Y coordinate for the game element
     * @param newPosX new Y coord to set
     */
	public void setPosY(int newPosY);
	
	/**
     * Gets Y coord of the element
     * @return Y coord
     */
	public int getPosY();
	
	/**
	 * Draws the element
	 * @param g graphic
	 */
	public void drawElement(Graphics g);
	
	/**
	 * Area of the element
	 * @return rectangle of the area
	 */
	public Rectangle getArea();
}
