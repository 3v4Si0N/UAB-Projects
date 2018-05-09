package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import collector.ColorCollector;
import collector.DefaultColorCollector;
import io.DefaultIO;
import io.IO;

/**
 * Game controller
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class DefaultGameController implements GameController {
	
	/**
     * Instance of the class
     */
	private static DefaultGameController instance = null;

	/**
	 * Max colors inside the computer list
	 */
	private static final int MAX_COLORS_COMPUTER_LIST = 4;
	
	/**
	 * Object color collector to get the colors
	 */
	private ColorCollector colorCollector;
	
	/**
	 * The color list which the computer plays with
	 */
	private List<String> computerColorList;

	/**
	 * States of the player's list position
	 */
	private static final String state1 = "X"; // Hit
	private static final String state2 = "-"; // Fail
	private static final String state3 = "O"; // It is in the list, but the position is incorrect
	
	private static final String ERROR_MESSAGE = "AN ERROR HAS OCCURRED";
	private static final int opportunities = 5;
	
	/**
	 * Default constructor
	 */
	private DefaultGameController() {
		this.colorCollector = DefaultColorCollector.getInstance();
	}

	/**
	 * Gets the instance of the class
	 * @return instance of the class
	 */
	public static DefaultGameController getInstance() {
		if (instance == null) {
			instance = new DefaultGameController();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean generateComputerColorList() {
	    
	    this.computerColorList = new ArrayList<String>();
		boolean result = false;
		String colorToAdd = this.getRandomColor();
		
		for (int i = 0; i < MAX_COLORS_COMPUTER_LIST; i++) {
		    while (this.computerColorList.contains(colorToAdd)) {
		        colorToAdd = this.getRandomColor();
		        //System.out.println(colorToAdd);
		    }
		    result = (this.computerColorList.add(colorToAdd));
		}
		return result;
	}
	
	/**
	 * Gets a random color from the color collector.
	 * @return random color
	 */
	private String getRandomColor() {
	    return colorCollector.getColorList().get(
	            (new Random(System.currentTimeMillis()))
	                    .nextInt(this.colorCollector.numberOfColors())
	                    );
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<String> getComputerColorList() {
		return this.computerColorList;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<String> getColorListForUser() {
	    return this.colorCollector.getColorList();
	}

	/**
     * {@inheritDoc}
     * Mostrar la lista del computer como *, *, *, *. 
     * Luego mostrar una lista con todos los colores posibles que puede usar el usuario, 
     * es decir, una lista con los 6 colores. Luego el usuario introducirá 4 colores y 
     * se comprobarán las listas. Y se tendrán como mucho 5 oportunidades, si no pierdes
     */
	@Override
	public boolean play() {
		boolean win = false;
		IO io = DefaultIO.getInstance();
		List<String> computerList, totalColorList, userColorList, answerList = null;
		String tClist, uClist, aList = null;
		int count = 0;
		
		// Check that computerList has been generated
		if (generateComputerColorList()) {
			computerList = getComputerColorList();
			io.printMessage("Computer color list: *, *, *, * \n");
			//io.printMessage(computerList + "\n");
			
			// Get total color list
			totalColorList = getColorListForUser();
			tClist = io.listToString(totalColorList);
			io.printMessage("Colors that you can use: " + tClist + "\n");
			
			while (count < opportunities) {
				io.printMessage("You have " + (opportunities-count) + " opportunities \n");
				
				//Create userColorList
				userColorList = createUserColorList(computerList);
				uClist = io.listToString(userColorList);
				io.printMessage("Your list: " + uClist + "\n");
				
				answerList = getAnswer(computerList, userColorList);
				aList = io.listToString(answerList);
				io.printMessage("Result: " + aList + "\n");
				
				if (aList.equals("X, X, X, X")) {
					win = true;
					io.printMessage("you've won!!!!");
					break;
				}
				
				count++;
				if (count == opportunities) {
					io.printMessage("you've lost!! :(");
				}
			}
		} else {
			io.printMessage(ERROR_MESSAGE);
		}
		
		return win;
	}
	
	/**
	 * Adds to a list the colors that the user wants
	 * @return userList
	 */
	public List<String> createUserColorList(List<String> computerList) {
		List<String> userList = new ArrayList<String>();
		
		int i = 0;
		String color = null;
		IO io = DefaultIO.getInstance();
		while (i < computerList.size()) {
			io.printMessage("Color["+i+"]: ");
			color = io.getUserAnswer();
			while ((color == ERROR_MESSAGE) || userList.contains(color)) {
				io.printMessage("Color["+i+"]: ");
				color = io.getUserAnswer();
			}
			
			userList.add(color);
			i++;
		}
		
		return userList;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public boolean compareColorList(
	        final List<String> computerList,
	        final List<String> userInputList) {
	    
	    boolean result = false;
	    
	    if ((computerList != null) && (userInputList != null)
	            && (computerList.size() == 4) && (userInputList.size() == 4)){
	        
	        // Check that the lists are not void strings
	        if (checkNotVoidStringList(computerList)
	                && checkNotVoidStringList(userInputList)) {
	            // Compare the two lists
	            int equalValues = 0;
	            for (int i = 0; i < computerList.size(); i++) {
	                equalValues += (computerList.get(i).equals(userInputList.get(i)) ? 1 : 0);
	            }
	            result = (equalValues == 4);
	        }
	    }

		return result;
	}
	
	/**
	 * Checks that a list has no void string value
	 * @param listToCheck list to check
	 * @return true if each element in the list is a non string void value
	 */
	private boolean checkNotVoidStringList(final List<String> listToCheck) {
	    boolean result = true;
	    int i = 0;
        while (i < listToCheck.size()) {
            result = (listToCheck.get(i) != "");
            i++;
            if (!result) {
            	break;
            }
        }
        return result;
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public List<String> getAnswer(
            final List<String> computerList,
            final List<String> userInputList) {
        
        List<String> answer = new ArrayList<String>();
        if (compareColorList(computerList, userInputList)) {
        	for (int i = 0; i < computerList.size(); i++) {
        		answer.add("X");
        	}
        } else {
        	for (int i = 0; i < userInputList.size(); i++) {
        		for (int j = 0; j < computerList.size(); j++) {
        			if (i == j) {
        				if(userInputList.get(i).equals(computerList.get(j))) {
            				answer.add(state1);
        				} else {
        					if ((i == (computerList.size()-1)) && 
        							answer.size() != computerList.size()) {
            					answer.add(state2);
            				}
        				}
        			} else {
        				if (userInputList.get(i).equals(computerList.get(j))) {
            				answer.add(state3);
        				} 
        				
        				if ((j == (computerList.size()-1)) && 
        						(answer.size() != (i+1))) {
        					answer.add(state2);
        				}
        			}
        		}
        	}
        }
        return answer;
    }
}
