package collector;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for the ColorCollector
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class MockColorCollector implements ColorCollector {

    /**
     * {@inheritDoc}
     */
    @Override
	public String getRed() {
		return "red";
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public String getBlue() {
		return "blue";
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public String getYellow() {
		return "yellow";
	}

	/**
     * {@inheritDoc}
     */
    @Override
	public String getGreen() {
		return "green";
	}

	/**
     * {@inheritDoc}
     */
    @Override
	public String getOrange() {
		return "orange";
	}

	/**
     * {@inheritDoc}
     */
    @Override
	public String getPurple() {
		return "purple";
	}

	/**
     * {@inheritDoc}
     */
    @Override
	public int numberOfColors() {
		return 6;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("serial")
	public List<String> getColorList() {
		return new ArrayList<String>() {
			{
		        add(new String("red"));
		        add(new String("blue"));
		        add(new String("yellow"));
		        add(new String("green"));
		        add(new String("orange"));
		        add(new String("purple"));
		    }
		};
	}
}
