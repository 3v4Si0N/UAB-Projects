package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import collector.ColorCollector;
import collector.DefaultColorCollector;

/**
 * Default implementation of the IO interface
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class DefaultIO implements IO {

	/**
	 * Instance of the class
	 */
	private static DefaultIO instance = null;
	
	/**
	 * Default error message
	 */
	private static final String ERROR_MESSAGE = "AN ERROR HAS OCCURRED";

	/**
	 * Default constructor
	 */
	private DefaultIO() {

	}

	/**
	 * {@inheritDoc}
	 */
	public static DefaultIO getInstance() {

		if (instance == null) {

			instance = new DefaultIO();

		}
		return instance;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public String getUserAnswer() {
				
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    	String answer = ERROR_MESSAGE;
    	ColorCollector collector = DefaultColorCollector.getInstance();
    	List<String> colorList = collector.getColorList();
		
    	try {
			answer = br.readLine();
			answer = answer.toLowerCase();
			
			if (!colorList.contains(answer)) {
	        	answer = ERROR_MESSAGE;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return answer;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String listToString(List<String> list) {
		String result = "";
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (i < (list.size()-1)) {
					result += list.get(i) + ", ";
				} else {
					result += list.get(i);
				}
			}
		} else {
			result = ERROR_MESSAGE;
		}
		return result;
		
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
    public void printMessage(final String message) {
        System.out.print(message);
        System.out.print("");
    }
}
