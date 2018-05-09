package io;

import java.util.List;

/**
 * Mock for the IO interface
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class MockIO implements IO {
	
	private int count;
	
	/**
	 * Default error message
	 */
	private static String ERROR_MESSAGE = "AN ERROR HAS OCCURRED";
	
	/**
	 * Constructor
	 */
	public MockIO() {
		count = 0;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getUserAnswer() {
		String answer = ERROR_MESSAGE;
		
		switch(count) {
			case 0:
				answer = "red";
				count ++;
				break;
			case 1:
				answer = "blue";
				count ++;
				break;
			case 2:
				answer = "yellow";
				count ++;
				break;
			case 3:
				answer = "green";
				count ++;
				break;
			case 4:
				answer = "orange";
				count ++;
				break;
			case 5:
				answer = "purple";
				count ++;
				break;
			case 6:
			    // user inputs a random word
			    answer = "AN ERROR HAS OCCURRED";
			    count++;
			    break;
		}
		
		return answer;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String listToString(List<String> colorList) {
	    if (colorList != null) {
	        return "red, blue, yellow, green";
	    }
	    return "AN ERROR HAS OCCURRED";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void printMessage(String message) {
	    System.out.print(message);
        System.out.print("");
	}

}
