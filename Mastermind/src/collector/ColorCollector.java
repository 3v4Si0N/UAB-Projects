package collector;

import java.util.List;

/**
 * Color collector, has the colors for the game
 * 
 * @author Ramon Guimerà. Héctor De Armas
 *
 */
public interface ColorCollector {
	
    /**
     * Gets color red
     * @return string red color
     */
	public String getRed();
	
	/**
     * Gets color blue
     * @return string blue color
     */
	public String getBlue();
	
	/**
     * Gets color yellow
     * @return string yellow color
     */
	public String getYellow();
	
	/**
     * Gets color green
     * @return string green color
     */
	public String getGreen();
	
	/**
     * Gets color orange
     * @return string orange color
     */
	public String getOrange();
	
	/**
     * Gets color purple
     * @return string purple color
     */
	public String getPurple();
	
	/**
     * Gets number of colors in the game
     * @return int number of colors
     */
	public int numberOfColors();
	
	/**
	 * Gets a list with every color in the game
	 * @return list with every color in the list
	 */
	public List<String> getColorList();
}
