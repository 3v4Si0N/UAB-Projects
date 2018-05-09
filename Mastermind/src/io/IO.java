package io;

import java.util.List;

/**
 * IO interface for the game.
 *
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public interface IO {

    /**
     * Gets the answer from the user
     * @return String with the user answer
     */
	public String getUserAnswer();
	
	/**
	 * Converts a list to a string
	 * @param list list to convert
	 * @return string as each element of the list concatenated
	 */
	public String listToString(List<String> list);
	
	/**
	 * Prints a message to the console
	 * @param message message to print
	 */
	public void printMessage(final String message);
}
