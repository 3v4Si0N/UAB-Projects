package controller;

/*
 * Imported necessary libraries
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import tree.Node;

/**
 * Input/Output controller
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class IOController {
    
    /**
     * Instance of the class
     */
    private static IOController instance = null;
    
    /**
     * Default error message
     */
    private static final String ERROR_MESSAGE = "AN ERROR HAS OCCURRED";
    
    /**
     * Default constructor
     */
    private IOController() {
    }
    
    /**
     * Gets instance of the class
     * @return instance of the class
     */
    public static IOController getInstance() {
        if (instance == null) {
            instance = new IOController();
        }
        return instance;
    }
    
    /**
     * Enters an answer from the user
     * @return the answer if it's yes or no, ERROR_MESSAGE otherwise
     */
    public String enterAnswer() {
    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String answer = ERROR_MESSAGE;
		
    	try {
			answer = br.readLine();
			answer = answer.toLowerCase();
			if ((!answer.equals("yes") && !answer.equals("no")) || answer.length() > Integer.MAX_VALUE) {
	        	answer = ERROR_MESSAGE;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}

        return answer;
    }
    
    /**
     * Enters the answer for when root asks it's question
     * @return the string typed, ERROR_MESSAGE otherwise
     */
    public String enterBranch() {
    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String branch = ERROR_MESSAGE;
		try {
			branch = br.readLine();
			branch = branch.toLowerCase();
			if ((!branch.equals("animal") 
			    && !branch.equals("vegetable") 
			    && !branch.equals("mineral"))
			    || branch.length() > Integer.MAX_VALUE) {
	        	branch = ERROR_MESSAGE;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}

        return branch;
    }
    
    /**
     * Enters the text for a leaf
     * @return the text from the leaf, or ERROR_MESSAGE if the input was not correctly typed
     */
    public String enterLeafText() {
    	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String leafText = ERROR_MESSAGE;
		try {
			leafText = br.readLine();
			leafText = leafText.toLowerCase();
			if (leafText == null || leafText == "" 
			    || leafText == "\r\n" || leafText.length() > Integer.MAX_VALUE) {
				leafText = ERROR_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

        return leafText;
    }
    
    /**
     * Shows the text from a node
     * @param node node to show the text from
     * @return the text from the node, or ERROR_MESSAGE if the node was null
     */
    public String showTextFromNode(final Node node) {
    	String result = ERROR_MESSAGE;
    	if (node != null) {
    		result = node.getText();
    	}
    	this.printMessage(result + "?");
    	return result;
    }
    
    /**
     * Prints a message to the console
     * @param message message to print
     */
    public void printMessage(final String message) {
        System.out.print(message);
        System.out.print("");
    }
}
