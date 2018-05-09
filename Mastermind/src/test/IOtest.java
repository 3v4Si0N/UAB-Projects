package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import game.MockGameController;
import io.IO;
import io.MockIO;

public class IOtest {
	
	private IO io = new MockIO();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static Logger log = Logger.getLogger( "junit.IOtest" );
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

	@Test
	public void testGetUserAnswer() {
		
		// Test with DefaultIO
		log.info("Red color");
		String result = io.getUserAnswer();
		String expected = "red";
		assertEquals(expected, result);
		
		log.info("Blue color");
		result = io.getUserAnswer();
		expected = "blue";
		assertEquals(expected, result);
		
		log.info("Yellow color");
		result = io.getUserAnswer();
		expected = "yellow";
		assertEquals(expected, result);
		
		log.info("Green color");
		result = io.getUserAnswer();
		expected = "green";
		assertEquals(expected, result);
		
		log.info("Orange color");
		result = io.getUserAnswer();
		expected = "orange";
		assertEquals(expected, result);
		
		log.info("Purple color");
		result = io.getUserAnswer();
		expected = "purple";
		assertEquals(expected, result);
		
		log.info("Random word");
		result = io.getUserAnswer();
		expected = "AN ERROR HAS OCCURRED";
		assertEquals(expected, result);
	}
	
	@Test
	public void testlistToString() {
		MockGameController game = MockGameController.getInstance();
		game.generateComputerColorList();
		
		List<String> list = game.getComputerColorList();
		String expected = "red, blue, yellow, green";
		String result = io.listToString(list);
		assertEquals(expected, result);
		
		list = null;
		expected = "AN ERROR HAS OCCURRED";
		result = io.listToString(list);
		assertEquals(expected, result);
	}
	
	@Test
    public void testPrintMessage() {
        io.printMessage("test message");
        assertEquals("test message", outContent.toString());
    }

}
