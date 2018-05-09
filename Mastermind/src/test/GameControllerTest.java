package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import collector.ColorCollector;
import collector.DefaultColorCollector;
import game.DefaultGameController;
import game.GameController;
import game.MockGameController;

public class GameControllerTest {

	@Test
    public void testGetInstance() {
        GameController result = DefaultGameController.getInstance();
        GameController expected = DefaultGameController.getInstance();
        assertEquals(expected, result);
    }
	
	@Test
	public void testGenerateComputerColorList() {
	    // Test that the list can be generated
		GameController gameController = DefaultGameController.getInstance();
		boolean result = gameController.generateComputerColorList();
		assertTrue(result);
		
		// Test that the list generated has size 4 and no more
		int computerListExpectedSize = 4;
		int sizeResult = gameController.getComputerColorList().size();
		assertEquals(computerListExpectedSize, sizeResult);
		
		// Test that the list has no repeated colors
		boolean result2 = true;
		String color = "";
		List<String> subList = new ArrayList<String>();
		
		for (int i = 1; i < gameController.getComputerColorList().size(); i++) {
		    color = gameController.getComputerColorList().get(i);
		    subList = gameController.getComputerColorList()
		            .subList(i + 1, gameController.getComputerColorList().size());
		    
		    result2 = (subList.contains(color) ? false : result2);
		}
		
		assertTrue(result2);
	}
	
	@Test
	public void testGetComputerColorList() {
		ColorCollector colorCollector = DefaultColorCollector.getInstance();
		GameController gameController = DefaultGameController.getInstance();
		
	    // Test that every color in the list is a valid color
		gameController.generateComputerColorList();
		List<String> computerList = gameController.getComputerColorList();
		boolean colorCorrect = false;
		for (String color : computerList) {
			colorCorrect = (colorCollector.getColorList().contains(color) ? true : colorCorrect);
		}
		assertTrue(colorCorrect);
		
		// Test that the list generated equals to the expected list
		// In order to the test to succeed, we must do it with the mock, due to having a random list every time
		gameController = MockGameController.getInstance();
		gameController.generateComputerColorList();
		List<String> resultList = gameController.getComputerColorList();
		List<String> expectedList = new ArrayList<String>();
		expectedList.add(colorCollector.getBlue());
		expectedList.add(colorCollector.getGreen());
		expectedList.add(colorCollector.getOrange());
		expectedList.add(colorCollector.getPurple());
		
		boolean result = false;
		for (int i = 0; i < resultList.size(); i++) {
		    result = (resultList.get(i).equals(expectedList.get(i)) ? true : result);
		}
		
		assertTrue(result);
	}
	
	@Test
	public void testGetColorListForUser() {
	    List<String> expectedList = DefaultColorCollector.getInstance().getColorList();
	    List<String> resultList = DefaultGameController.getInstance().getColorListForUser();
	    boolean result = false;
	    
	    // Test that the list with colors the user plays is equal to every color in the game
	    for (int i = 0; i < resultList.size(); i++) {
            result = (resultList.get(i).equals(expectedList.get(i)) ? true : result);
        }
	    assertTrue(result);
	}
	
	@Test
	public void testCreateUserColorList() {
		GameController gameController = DefaultGameController.getInstance();
        // Loop simple testing with computerList.size() == 0
        List<String> inputList = new ArrayList<String>();
        
        List <String> result = gameController.createUserColorList(inputList);
        assertEquals(0, result.size());
        
        // Loop simple testing with computerList.size() == 1
        List<String> inputList2 = new ArrayList<String>();
        inputList2.add("blue");
        
        List <String> result2 = gameController.createUserColorList(inputList2);
        assertEquals(1, result2.size());
        
        // Loop simple testing with computerList.size() == 2
        List<String> inputList3 = new ArrayList<String>();
        inputList3.add("blue");
        inputList3.add("red");
        
        List <String> result3 = gameController.createUserColorList(inputList3);
        assertEquals(2, result3.size());
        
        // Loop simple testing with computerList.size() == MAXSIZE_computerList-1 (3)
        List<String> inputList4 = new ArrayList<String>();
        inputList4.add("blue");
        inputList4.add("red");
        inputList4.add("green");
        inputList4.add("yellow");
        
        List <String> result4 = gameController.createUserColorList(inputList4);
        assertEquals(4, result4.size());
	}
	
	@Test
	public void testCompareColorList() {
	    GameController gameController = DefaultGameController.getInstance();
	    boolean result = false;
	    
	    // Test null input
	    List<String> inputList1 = null;
	    List<String> inputList2 = null;
	    
	    result = gameController.compareColorList(inputList1, inputList2);
	    assertFalse(result);
	    
	    // CONDITION COVERAGE Null input
        inputList2 = new ArrayList<String>();
        result = gameController.compareColorList(inputList1, inputList2);
        assertFalse(result);
        
        inputList1 = new ArrayList<String>();
        inputList2 = null;
        result = gameController.compareColorList(inputList1, inputList2);
        assertFalse(result);
	    
	    // Test lists with different sizes
	    List<String> inputList3 = new ArrayList<String>();
	    List<String> inputList4 = new ArrayList<String>();
	    
	    inputList3.add("first color");
	    inputList4.add("second color");
	    inputList4.add("third color");
	    
	    result = gameController.compareColorList(inputList3, inputList4);
	    assertFalse(result);
	    
	    // Test lists with sizes different than 4
	    List<String> inputList5 = new ArrayList<String>();
        List<String> inputList6 = new ArrayList<String>();
        
        inputList5.add("first color");
        inputList5.add("first color");
        inputList5.add("first color");
        inputList5.add("first color");
        inputList5.add("first color");
        inputList6.add("second color");
        inputList6.add("third color");
        inputList6.add("second color");
        inputList6.add("third color");
        inputList6.add("third color");
        
        result = gameController.compareColorList(inputList5, inputList6);
        assertFalse(result);
        
        // CONDITION COVERAGE size different than 4
        inputList5 = new ArrayList<String>();
        inputList5.add("first color");
        inputList5.add("first color");
        inputList5.add("first color");
        inputList5.add("first color");
        
        result = gameController.compareColorList(inputList5, inputList6);
        assertFalse(result);
        
        inputList5.add("first color");
        
        inputList6 = new ArrayList<String>();
        inputList6.add("first color");
        inputList6.add("first color");
        inputList6.add("first color");
        inputList6.add("first color");
        
        result = gameController.compareColorList(inputList5, inputList6);
        assertFalse(result);
        
	    // Test lists with values equal to "" (void string)
        List<String> inputList7 = new ArrayList<String>();
        List<String> inputList8 = new ArrayList<String>();
        
        inputList5.add("");
        inputList5.add("");
        inputList5.add("");
        inputList5.add("");
        inputList6.add("");
        inputList6.add("");
        inputList6.add("");
        inputList6.add("");
        
        result = gameController.compareColorList(inputList7, inputList8);
        assertFalse(result);
        
	    // Test different lists
        List<String> inputList9 = new ArrayList<String>();
        gameController.generateComputerColorList();
        List<String> inputList10 = gameController.getComputerColorList();
        
        inputList9.add("green");
        inputList9.add("green");
        inputList9.add("green");
        inputList9.add("green");
        
        result = gameController.compareColorList(inputList9, inputList10);
        assertFalse(result);
        
	    // Test equal lists
        List<String> inputList11 = new ArrayList<String>();
        List<String> inputList12 = new ArrayList<String>();
        
        inputList11.add("blue");
        inputList11.add("green");
        inputList11.add("orange");
        inputList11.add("red");
        inputList12.add("blue");
        inputList12.add("green");
        inputList12.add("orange");
        inputList12.add("red");
        
        result = gameController.compareColorList(inputList11, inputList12);
        assertTrue(result);
        
        // Decision coverage, userList with void String
        List<String> inputList13 = new ArrayList<String>();
        List<String> inputList14 = new ArrayList<String>();
        
        inputList13.add("green");
        inputList13.add("orange");
        inputList13.add("brown");
        inputList13.add("red");
        inputList14.add("blue");
        inputList14.add("green");
        inputList14.add("orange");
        inputList14.add("");
        
        result = gameController.compareColorList(inputList13, inputList14);
        assertFalse(result);
        
        // Decision coverage, computerList with void String
        List<String> inputList15 = new ArrayList<String>();
        List<String> inputList16 = new ArrayList<String>();
        
        inputList15.add("green");
        inputList15.add("orange");
        inputList15.add("brown");
        inputList15.add("");
        inputList16.add("blue");
        inputList16.add("green");
        inputList16.add("orange");
        inputList16.add("red");
        
        result = gameController.compareColorList(inputList15, inputList16);
        assertFalse(result);
        
        // PATH coverage
        // PATH 1
        List<String> inputListPathCov1 = null;
        List<String> inputListPathCov2 = null;
        boolean resultPath1 = gameController.compareColorList(inputListPathCov1, inputListPathCov2);
        assertFalse(resultPath1);
        
        // PATH 2
        List<String> inputListPathCov3 = new ArrayList<String>();
        List<String> inputListPathCov4 = new ArrayList<String>();
        
        inputListPathCov3.add("");
        inputListPathCov3.add("");
        inputListPathCov3.add("");
        inputListPathCov3.add("");
        inputListPathCov4.add("");
        inputListPathCov4.add("");
        inputListPathCov4.add("");
        inputListPathCov4.add("");
        
        boolean resultPath2 = gameController.compareColorList(inputListPathCov3, inputListPathCov4);
        assertFalse(resultPath2);
        
        // PATH 3
        List<String> inputListPathCov5 = new ArrayList<String>();
        List<String> inputListPathCov6 = new ArrayList<String>();
        
        inputListPathCov5.add("blue");
        inputListPathCov5.add("red");
        inputListPathCov5.add("green");
        inputListPathCov5.add("yellow");
        inputListPathCov6.add("blue");
        inputListPathCov6.add("red");
        inputListPathCov6.add("green");
        inputListPathCov6.add("yellow");
        
        boolean resultPath3 = gameController.compareColorList(inputListPathCov5, inputListPathCov6);
        assertTrue(resultPath3);
	}
	
	@Test
	public void testGetAnswer() {
		GameController gameController = DefaultGameController.getInstance();
		
		// Test with the same lists
		List<String> inputList = new ArrayList<String>();
	    List<String> inputList2 = new ArrayList<String>();
	    
	    inputList.add("blue");
	    inputList.add("red");
	    inputList.add("green");
        inputList.add("orange");
        
	    inputList2.add("blue");
	    inputList2.add("red");
	    inputList2.add("green");
        inputList2.add("orange");
	    
	    List<String> result = gameController.getAnswer(inputList, inputList2);
	    List<String> expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("X");
	    expected.add("X");
	    expected.add("X");
	    
	    assertEquals(expected, result); 
	    
	    // Tests with state1 and state3
	    // Test 1
	    List<String> inputList3 = new ArrayList<String>();
	    
	    inputList3.add("red");
	    inputList3.add("blue");
	    inputList3.add("green");
        inputList3.add("orange");
        
        result = gameController.getAnswer(inputList, inputList3);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("O");
	    expected.add("X");
	    expected.add("X");
	
	    assertEquals(expected, result);
	    
	    // Test2
	    List<String> inputList4 = new ArrayList<String>();
	    
	    inputList4.add("blue");
	    inputList4.add("red");
        inputList4.add("orange");
        inputList4.add("green");
        
        result = gameController.getAnswer(inputList, inputList4);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("X");
	    expected.add("O");
	    expected.add("O");
	
	    assertEquals(expected, result);
	    
	    // Test3
	    List<String> inputList5 = new ArrayList<String>();
	    
	    inputList5.add("blue");
	    inputList5.add("orange");
	    inputList5.add("green");
        inputList5.add("red");
        
        
        result = gameController.getAnswer(inputList, inputList5);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("O");
	    expected.add("X");
	    expected.add("O");
	
	    assertEquals(expected, result);
	    
	    // Test4
	    List<String> inputList6 = new ArrayList<String>();
	    
	    inputList6.add("green");
        inputList6.add("red");
        inputList6.add("blue");
        inputList6.add("orange");
        
        result = gameController.getAnswer(inputList, inputList6);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("X");
	    expected.add("O");
	    expected.add("X");
	
	    assertEquals(expected, result);
	    
	    // Test with state1 and state2
	    // Test1
	    List<String> inputList7 = new ArrayList<String>();
	    
	    inputList7.add("yellow");
	    inputList7.add("red");
	    inputList7.add("green");
        inputList7.add("orange");
        
        result = gameController.getAnswer(inputList, inputList7);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("X");
	    expected.add("X");
	    expected.add("X");
	    
	    assertEquals(expected, result);
	    
	    // Test2
	    List<String> inputList8 = new ArrayList<String>();
	    
	    inputList8.add("blue");
	    inputList8.add("yellow");
	    inputList8.add("green");
        inputList8.add("orange");
        
        result = gameController.getAnswer(inputList, inputList8);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("-");
	    expected.add("X");
	    expected.add("X");
	    
	    assertEquals(expected, result);
	    
	    // Test3
	    List<String> inputList9 = new ArrayList<String>();
	    
	    inputList9.add("blue");
	    inputList9.add("red");
	    inputList9.add("yellow");
        inputList9.add("orange");
        
        result = gameController.getAnswer(inputList, inputList9);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("X");
	    expected.add("-");
	    expected.add("X");
	    
	    assertEquals(expected, result);
	    
	    // Test3
	    List<String> inputList10 = new ArrayList<String>();
	    
	    inputList10.add("blue");
	    inputList10.add("red");
	    inputList10.add("green");
        inputList10.add("yellow");
        
        result = gameController.getAnswer(inputList, inputList10);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("X");
	    expected.add("X");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test4
	    List<String> inputList11 = new ArrayList<String>();
	    
	    inputList11.add("blue");
	    inputList11.add("gray");
	    inputList11.add("violet");
        inputList11.add("yellow");
        
        result = gameController.getAnswer(inputList, inputList11);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("-");
	    expected.add("-");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test5
	    List<String> inputList12 = new ArrayList<String>();
	    
	    inputList12.add("gray");
	    inputList12.add("red");
	    inputList12.add("violet");
        inputList12.add("yellow");
        
        result = gameController.getAnswer(inputList, inputList12);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("X");
	    expected.add("-");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test6
	    List<String> inputList13 = new ArrayList<String>();
	    
	    inputList13.add("violet");
	    inputList13.add("gray");
	    inputList13.add("green");
        inputList13.add("yellow");
        
        result = gameController.getAnswer(inputList, inputList13);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("-");
	    expected.add("X");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test7
	    List<String> inputList14 = new ArrayList<String>();
	    
	    inputList14.add("yellow");
	    inputList14.add("gray");
	    inputList14.add("violet");
        inputList14.add("orange");
        
        result = gameController.getAnswer(inputList, inputList14);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("-");
	    expected.add("-");
	    expected.add("X");
	    
	    assertEquals(expected, result);
	    
	    // Test with state2 and state3
	    // Test1
	    List<String> inputList15 = new ArrayList<String>();
	    
	    inputList15.add("orange");
	    inputList15.add("yellow");
	    inputList15.add("purple");
        inputList15.add("gray");
        
        result = gameController.getAnswer(inputList, inputList15);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("-");
	    expected.add("-");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test2
	    List<String> inputList16 = new ArrayList<String>();
	    
	    inputList16.add("violet");
	    inputList16.add("orange");
	    inputList16.add("purple");
        inputList16.add("gray");
        
        result = gameController.getAnswer(inputList, inputList16);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("O");
	    expected.add("-");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test3
	    List<String> inputList17 = new ArrayList<String>();
	    
	    inputList17.add("violet");
	    inputList17.add("purple");
	    inputList17.add("orange");
        inputList17.add("gray");
        
        result = gameController.getAnswer(inputList, inputList17);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("-");
	    expected.add("O");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	    // Test4
	    List<String> inputList18 = new ArrayList<String>();
	    
	    inputList18.add("violet");
	    inputList18.add("purple");
	    inputList18.add("gray");
        inputList18.add("green");
        
        result = gameController.getAnswer(inputList, inputList18);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("-");
	    expected.add("-");
	    expected.add("O");
	    
	    assertEquals(expected, result);
	    
	    // Test5
	    List<String> inputList19 = new ArrayList<String>();
	    
	    inputList19.add("violet");
	    inputList19.add("blue");
	    inputList19.add("red");
        inputList19.add("green");
        
        result = gameController.getAnswer(inputList, inputList19);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("O");
	    expected.add("O");
	    expected.add("O");
	    
	    assertEquals(expected, result);
	    
	    // Test6
	    List<String> inputList20 = new ArrayList<String>();
	    
	    inputList20.add("red");
	    inputList20.add("violet");
	    inputList20.add("blue");
        inputList20.add("green");
        
        result = gameController.getAnswer(inputList, inputList20);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("-");
	    expected.add("O");
	    expected.add("O");
	    
	    assertEquals(expected, result);
	    
	    // Test7
	    List<String> inputList21 = new ArrayList<String>();
	    
	    inputList21.add("red");
	    inputList21.add("blue");
	    inputList21.add("violet");
        inputList21.add("green");
        
        result = gameController.getAnswer(inputList, inputList21);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("O");
	    expected.add("-");
	    expected.add("O");
	    
	    assertEquals(expected, result);
	    
	    // Test7
	    List<String> inputList22 = new ArrayList<String>();
	    
	    inputList22.add("red");
	    inputList22.add("blue");
	    inputList22.add("orange");
        inputList22.add("violet");
        
        result = gameController.getAnswer(inputList, inputList22);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("O");
	    expected.add("O");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	    
	 // Test with state1, state2 and state3
	    // Test1
	    List<String> inputList23 = new ArrayList<String>();
	    
	    inputList23.add("blue");
	    inputList23.add("red");
	    inputList23.add("yellow");
        inputList23.add("green");
        
        result = gameController.getAnswer(inputList, inputList23);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("X");
	    expected.add("-");
	    expected.add("O");
	    
	    assertEquals(expected, result);
	    
	    // Test2
	    List<String> inputList24 = new ArrayList<String>();
	    
	    inputList24.add("blue");
	    inputList24.add("gray");
	    inputList24.add("yellow");
        inputList24.add("green");
        
        result = gameController.getAnswer(inputList, inputList24);
	    expected = new ArrayList<String>();
	    expected.add("X");
	    expected.add("-");
	    expected.add("-");
	    expected.add("O");
	    
	    assertEquals(expected, result);
	    
	    // Test3
	    List<String> inputList25 = new ArrayList<String>();
	    
	    inputList25.add("green");
	    inputList25.add("gray");
	    inputList25.add("yellow");
        inputList25.add("orange");
        
        result = gameController.getAnswer(inputList, inputList25);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("-");
	    expected.add("-");
	    expected.add("X");
	    
	    // Test4
	    List<String> inputList26 = new ArrayList<String>();
	    
	    inputList26.add("gray");
	    inputList26.add("blue");
	    inputList26.add("green");
        inputList26.add("orange");
        
        result = gameController.getAnswer(inputList, inputList26);
	    expected = new ArrayList<String>();
	    expected.add("-");
	    expected.add("O");
	    expected.add("X");
	    expected.add("X");
	    
	    assertEquals(expected, result);
	    
	    // Test5
	    List<String> inputList27 = new ArrayList<String>();
	    
	    inputList27.add("orange");
	    inputList27.add("red");
	    inputList27.add("green");
        inputList27.add("gray");
        
        result = gameController.getAnswer(inputList, inputList27);
	    expected = new ArrayList<String>();
	    expected.add("O");
	    expected.add("X");
	    expected.add("X");
	    expected.add("-");
	    
	    assertEquals(expected, result);
	}

}
