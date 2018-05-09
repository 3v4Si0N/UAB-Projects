package controller;

/*
 * Imported necessary libraries
 */
import tree.Node;
import tree.NodeQuestion;
import tree.NodeRoot;

/**
 * Controller for the game
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class GameController {

    /**
     * Instance of the class
     */
	private static GameController instance = null;
	
	/**
	 * File name for games to be saved at
	 */
	private static final String GAME_FILENAME = "last_game";
	
	/**
	 * Default error message
	 */
	private static final String ERROR_MESSAGE = "AN ERROR HAS OCCURRED";
	
	/**
	 * Finish message for the user
	 */
	private static final String FINISH_MESSAGE = "FINISH";

	/**
	 * Default constructor
	 */
	private GameController() {
	}

	/**
	 * Gets the instance of the class
	 * @return instance of the class
	 */
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	/**
	 * Plays a game
	 * @return true if the user wins, false otherwise
	 */
	public boolean play() {
		/* Initialize controllers */
		TreeController treeController = TreeController.getInstance();
		IOController ioController = IOController.getInstance();

		/* Initialize saver */
		SerSaver saver = SerSaver.getInstance();

		/* Initialize game variables */
		boolean finish    = false;
		String userAnswer = "";
		Node currentNode  = null;
		Node root = (NodeRoot) saver.load(GAME_FILENAME);

		/* START GAME */
		if (root == null) {
			root = treeController.createEmptyTree();
		}

		while (!userAnswer.equals("animal") && !userAnswer.equals("vegetable") && !userAnswer.equals("mineral")) {
			ioController.showTextFromNode(root);
			userAnswer = ioController.enterBranch();
		}

		switch (userAnswer) {
		case "animal":
			currentNode = ((NodeRoot) root).getAnimalBranch();
			break;
		case "vegetable":
			currentNode = ((NodeRoot) root).getVegetableBranch();
			break;
		case "mineral":
			currentNode = ((NodeRoot) root).getMineralBranch();
			break;
		default:
			break;
		}

		// 1: do while not finish
		while (!finish) {
			

			// check until userAnswer has been entered correctly
			while (!userAnswer.equals("yes") && !userAnswer.equals("no")) {
				// show question from node
				ioController.showTextFromNode(currentNode);
				// get answer from user
				userAnswer = ioController.enterAnswer();
			}

			// check win condition
			if (userAnswer.equals("yes")) {
				if (!treeController.isNextYesNodeNull((NodeQuestion) currentNode)) {
					// load next node
					currentNode = ((NodeQuestion) currentNode).getNextYesNode();
				} else {
					// We win
					ioController.printMessage(FINISH_MESSAGE);
					finish = true;
				}
			} else {
				if (((NodeQuestion) currentNode).getNextNoNode() != null) {
					// load next node
					currentNode = ((NodeQuestion) currentNode).getNextNoNode();
				} else {
					// add new node to tree and finish
					ioController.printMessage("What were you thinking?");
					String newLeafAnswer = ioController.enterLeafText();

					ioController.printMessage(
					        "Now please type in a question that has a yes answer and will tell the difference between "
					        		+ currentNode.getText() + " and " + newLeafAnswer + ": ");
					String text = ioController.enterLeafText();

					treeController.addNewNode((NodeQuestion) currentNode, text, newLeafAnswer);

					ioController.printMessage(FINISH_MESSAGE);
					finish = true;
				}
			}
			userAnswer = "";
		}

		saver.save(root, GAME_FILENAME);
		return finish;
	}
}
