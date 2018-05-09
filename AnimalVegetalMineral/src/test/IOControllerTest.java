package test;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import controller.IOController;
import tree.NodeQuestion;
import tree.NodeRoot;

public class IOControllerTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static Logger log = Logger.getLogger( "junit.IOControllerTest" );
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testGetInstnance() {
        IOController ioControllerExpected = IOController.getInstance();
        IOController ioControllerResult = IOController.getInstance();
        assertEquals(ioControllerExpected, ioControllerResult);
    }    
    
    @Test
    public void testEnterAnswer() {
        String expectedYes = "yes";
        String expectedNo = "no";
        String expectedError = "AN ERROR HAS OCCURRED";
        
        log.info("Test with yes or no");
        String result = IOController.getInstance().enterAnswer();
        assertTrue(checkMultipleStrings(result, expectedYes, expectedNo));
        
        log.info("Test with random word");
        String result2 = IOController.getInstance().enterAnswer();
        assertEquals(expectedError, result2);
    }
    
    @Test
    public void testEnterBranch() {
        String animal = "animal";
        String vegetable = "vegetable";
        String mineral = "mineral";
        String expectedError = "AN ERROR HAS OCCURRED";
        
        log.info("Test with animal");
        String result = IOController.getInstance().enterBranch();
        assertEquals(animal, result);
        
        log.info("Test with vegetable");
        String result2 = IOController.getInstance().enterBranch();
        assertEquals(vegetable, result2);
        
        log.info("Test with mineral");
        String result3 = IOController.getInstance().enterBranch();
        assertEquals(mineral, result3);
        
        log.info("Test with random word");
        String result4 = IOController.getInstance().enterBranch();
        assertEquals(expectedError, result4);
    }
    
    @Test
    public void testEnterLeafText() {
        String correctText = "leaf";
        
        log.info("Test with leaf word");
        String result = IOController.getInstance().enterLeafText();
        assertEquals(correctText, result);
    }
    
    @Test
    public void testShowTextFromNode() {
    	NodeQuestion newQuestion = new NodeQuestion(null,
    	        "my question",
    	        null,
    	        null);
    	String expected = "my question";
    	String result = IOController.getInstance().showTextFromNode(newQuestion);
    	assertEquals(expected, result);
    	
    	String expected2 = "AN ERROR HAS OCCURRED";
    	String result2 = IOController.getInstance().showTextFromNode(null);
    	assertEquals(expected2, result2);
    	
    	NodeRoot root = new NodeRoot("my question", null, null, null);
    	String expected3 = "my question";
    	String result3 = IOController.getInstance().showTextFromNode(root);
    	assertEquals(expected3, result3);
    }
    
    @Test
    public void testPrintMessage() {
        IOController.getInstance().printMessage("test message");
        assertEquals("test message", outContent.toString());
    }
    
	private boolean checkMultipleStrings(String answer, String expectedYes, String expectedNo) {
		return (answer.equals(expectedYes) || answer.equals(expectedNo));
	}
}
