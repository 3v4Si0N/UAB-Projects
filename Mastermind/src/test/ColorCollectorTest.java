package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import collector.DefaultColorCollector;

public class ColorCollectorTest {
	
	private DefaultColorCollector collector = DefaultColorCollector.getInstance();
	
	@Test
    public void testGetInstance() {
		DefaultColorCollector result = DefaultColorCollector.getInstance();
		DefaultColorCollector expected = DefaultColorCollector.getInstance();
        assertEquals(expected, result);
    }

	@Test
	public void testGetRed() {
		String red = collector.getRed();
		assertEquals("red", red);
	}

	@Test
	public void testGetBlue() {
		String blue = collector.getBlue();
		assertEquals("blue", blue);
	}

	@Test
	public void testGetYellow() {
		String yellow = collector.getYellow();
		assertEquals("yellow", yellow);
	}

	@Test
	public void testGetGreen() {
		String green = collector.getGreen();
		assertEquals("green", green);
	}

	@Test
	public void testGetOrange() {
		String orange = collector.getOrange();
		assertEquals("orange", orange);
	}

	@Test
	public void testGetPurple() {
		String purple = collector.getPurple();
		assertEquals("purple", purple);
	}
	
	@Test
	public void testNumberOfColors() {
	    int resultColors = collector.numberOfColors();
	    assertEquals(6, resultColors);
	}
	
	@Test
	public void testGetColorList() {
	    List<String> expectedList = new ArrayList<String>();
	    List<String> resultList = collector.getColorList();
	    
	    expectedList.add(new String("red"));
	    expectedList.add(new String("blue"));
	    expectedList.add(new String("yellow"));
	    expectedList.add(new String("green"));
	    expectedList.add(new String("orange"));
	    expectedList.add(new String("purple"));
	    
	    boolean result = true;
	    for (int i = 0; i < expectedList.size(); i++) {
	        result = (expectedList.get(i).equals(resultList.get(i)) ? result : false);
	    }
	    
	    assertTrue(result);
	}

}
