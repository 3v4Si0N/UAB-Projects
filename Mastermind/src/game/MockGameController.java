package game;

import java.util.ArrayList;
import java.util.List;
import collector.ColorCollector;
import collector.MockColorCollector;

/**
 * Mock for the game controller
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class MockGameController implements GameController {

	private List<String> computerColorList;
	private ColorCollector colorCollector;
	private List<String> userColorList;
	private static MockGameController instance = null;
	
	/**
	 * Default controller
	 */
	private MockGameController() {
		this.computerColorList = new ArrayList<String>();
		this.colorCollector = new MockColorCollector();
		this.userColorList = new ArrayList<String>();
		this.userColorList.add(this.colorCollector.getBlue());
		this.userColorList.add(this.colorCollector.getGreen());
		this.userColorList.add(this.colorCollector.getOrange());
		this.userColorList.add(this.colorCollector.getPurple());
		this.userColorList.add(this.colorCollector.getRed());
		this.userColorList.add(this.colorCollector.getYellow());
	}
	
	/**
	 * Gets instance of the class
	 * @return instance of the class
	 */
	public static MockGameController getInstance() {
		if (instance == null) {
			instance = new MockGameController();
		}
		return instance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean generateComputerColorList() {
		if (this.computerColorList.add(this.colorCollector.getBlue())
		 && this.computerColorList.add(this.colorCollector.getGreen())
		 && this.computerColorList.add(this.colorCollector.getOrange())
		 && this.computerColorList.add(this.colorCollector.getPurple())) {
			return true;
		}
		return false;
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
    	return this.userColorList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean play() {
    	return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean compareColorList(final List<String> computerList, final List<String> userInputList) {
    	return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAnswer(List<String> computerList, List<String> userInputList) {
        List<String> answer = new ArrayList<String>();
        answer.add("X");
        answer.add("X");
        answer.add("X");
        answer.add("X");
        return answer;
    }

	@Override
	public List<String> createUserColorList(List<String> computerList) {
		List<String> userList = new ArrayList<String>();
        userList.add("blue");
        userList.add("red");
        userList.add("green");
        userList.add("yellow");
		return userList ;
	}
}
