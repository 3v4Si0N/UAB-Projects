package game;

import java.util.List;

public interface GameController {
	
	/**
	 * Generates the color list for the computer
	 * @return generates the list from scratch
	 */
    public boolean generateComputerColorList();
    
    /**
     * Obtains the computer color list
     * @return gets the computer list
     */
    public List<String> getComputerColorList();
    
    /**
     * Gets the color list for the user to choose from
     * @return gets every color in the game
     */
    public List<String> getColorListForUser();
    
    /**
     * Plays the game
     * @return true if the user won, false otherwise
     */
    public boolean play();
    
    /**
     * Compares the list between the computer and the player
     * @param computerList list of the computer
     * @param userInputList input made by the user
     * @return true if both list are equal, false in error or non-equal
     */
    public boolean compareColorList(
            final List<String> computerList,
            final List<String> userInputList);
    
    /**
     * Returns the answer after the user has entered it
     * @param computerList list of the computer
     * @param userInputList input made by the user
     * @return answer
     */
    public List<String> getAnswer(
            final List<String> computerList,
            final List<String> userInputList);

    /**
     * Return the user list
     * @param computerList
     * @return userList
     */
	public List<String> createUserColorList(
			List<String> computerList);
}
