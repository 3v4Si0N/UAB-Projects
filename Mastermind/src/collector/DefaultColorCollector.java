package collector;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the color collector
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class DefaultColorCollector implements ColorCollector {
	
	/**
	 * Static list of colors
	 */
	@SuppressWarnings("serial")
    private static final List<String> colorList = new ArrayList<String>() {
		{
	        add(new String("red"));
	        add(new String("blue"));
	        add(new String("yellow"));
	        add(new String("green"));
	        add(new String("orange"));
	        add(new String("purple"));
	    }
	};
	
	/**
     * Instance of the class
     */
	private static DefaultColorCollector instance = null;
	
	/**
	 * Default constructor
	 */
	private DefaultColorCollector() {
	}

	/**
	 * Gets the instance of the class
	 * @return instance of the class
	 */
	public static DefaultColorCollector getInstance() {
		if (instance == null) {
			instance = new DefaultColorCollector();
		}
		return instance;
	}

	/**
     * {@inheritDoc}
     */
    @Override
	public String getRed() {
		return colorList.get(0);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
	public String getBlue() {
		return colorList.get(1);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
	public String getYellow() {
		return colorList.get(2);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
	public String getGreen() {
		return colorList.get(3);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
	public String getOrange() {
		return colorList.get(4);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
	public String getPurple() {
		return colorList.get(5);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int numberOfColors() {
		return colorList.size();
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
	public List<String> getColorList() {
		return colorList;
	}
}
