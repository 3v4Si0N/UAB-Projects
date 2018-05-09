package controller;

/*
 * Imported necessary libraries
 */
import tree.Node;
import tree.NodeQuestion;
import tree.NodeRoot;

/**
 * Controller for the tree structure
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
public class TreeController {
	
    /**
     * Instance of the class
     */
	private static TreeController instance = null;
	
	/**
	 * Default constructor
	 */
	private TreeController() {
    }
    
	/**
	 * Getter of instance
	 * @return instance of the class
	 */
    public static TreeController getInstance() {
        if (instance == null) {
            instance = new TreeController();
        }
        return instance;
    }
    
    /**
     * Creates a new question node
     * @param lastNode father of the new node
     * @param text question of the new node
     * @param nextYesNode next yes question for the new node
     * @param nextNoNode next no question for the new node
     * @return new question node or null if a param is not given correctly
     */
    public NodeQuestion newNodeQuestion(
    		final Node lastNode,
	        final String text,
	        final NodeQuestion nextYesNode,
	        final NodeQuestion nextNoNode) {
        
        if (lastNode == null 
            || text == null
            || text == ""
            || text.equals(new String())) {
            
            return null;
        }
    	
		return (new NodeQuestion(
				lastNode,
		        text,
		        nextYesNode,
		        nextNoNode));    	
    }
    
    /**
     * Creates a new root node
     * @param text main question of the root node
     * @param firstAnimalQuestion first animal branch question
     * @param firstVegetableQuestion first vegetable branch question
     * @param firstMineralQuestion first mineral branch question
     * @return new root node or null if a param is not given correctly
     */
    public NodeRoot newNodeRoot(
            final String text,
            final NodeQuestion firstAnimalQuestion,
            final NodeQuestion firstVegetableQuestion,
            final NodeQuestion firstMineralQuestion) {
    	
        if (text == null
            || text == ""
            || text.equals(new String())) {
            
            return null;
        }
        
    	return (new NodeRoot(
                text,
                firstAnimalQuestion,
                firstVegetableQuestion,
                firstMineralQuestion));
    }
    
    /**
     * Creates an empty tree with default questions
     * @return root node of the new tree
     */
    public NodeRoot createEmptyTree() {
    	
    	NodeRoot root = new NodeRoot("Animal, Vegetable or Mineral", null, null, null);
    	
    	NodeQuestion animalBranch    = new NodeQuestion(root, "Es un pajaro", null, null);
    	NodeQuestion vegetableBranch = new NodeQuestion(root, "Es verde", null, null);
    	NodeQuestion mineralBranch   = new NodeQuestion(root, "Es brillante", null, null);
    	
    	NodeQuestion animal = new NodeQuestion(animalBranch, "Pensabas en un loro", null, null);
    	NodeQuestion vegetable = new NodeQuestion(vegetableBranch, "Pensabas en lechuga", null, null);
    	NodeQuestion mineral = new NodeQuestion(mineralBranch, "Pensabas en oro", null, null);
    	
    	animalBranch.setNextYesNode(animal);
    	vegetableBranch.setNextYesNode(vegetable);
    	mineralBranch.setNextYesNode(mineral);
    	
    	NodeQuestion animal2 = new NodeQuestion(animalBranch, "Pensabas en un perro", null, null);
    	NodeQuestion vegetable2 = new NodeQuestion(vegetableBranch, "Pensabas en un tomate", null, null);
    	NodeQuestion mineral2 = new NodeQuestion(mineralBranch, "Pensabas en cobre", null, null);
    	
    	animalBranch.setNextNoNode(animal2);
    	vegetableBranch.setNextNoNode(vegetable2);
    	mineralBranch.setNextNoNode(mineral2);
    	
    	root.setAnimalBranch(animalBranch);
    	root.setVegetableBranch(vegetableBranch);
    	root.setMineralBranch(mineralBranch);
    	
    	return root;
    }
    
    /**
     * Links the next question node
     * @param newNodeQuestion new node to link
     * @param newNextNextNode node to be linked to the new node
     * @param userAnswer answer given by the user
     * @return true if can be set, false in case a param is not well given
     */
    public boolean setNextNodeQuestion(
            final NodeQuestion newNodeQuestion,
            final NodeQuestion newNextNextNode,
            final boolean userAnswer) {
        
        boolean result = false;
        if (newNodeQuestion != null && newNextNextNode != null) {
            if (userAnswer) {
                result = newNodeQuestion.setNextYesNode(newNextNextNode);
            } else {
                result = newNodeQuestion.setNextNoNode(newNextNextNode);
            }
        }
        return result;
    }
    
    /**
     * Returns if the new next yes node is null or not
     * @param nextNode next node of the current node
     * @return true if next node next yes is null, false otherwise
     */
    public boolean isNextYesNodeNull(NodeQuestion nextNode) {    	
    	return (nextNode.getNextYesNode() == null);
    }
    
    /**
     * Adds a new node to the tree
     * @param currentNode current node of the game
     * @param text text for the new node
     * @param newLeafAnswer new text for the new node's son
     * @return the new node
     */
    public NodeQuestion addNewNode(
			NodeQuestion currentNode,
			String text,
			String newLeafAnswer) {
    	
    	NodeQuestion nextNode = null;
    	
    	if (currentNode == null) {
    		return nextNode;
    	}
		
		nextNode = newNodeQuestion(
				currentNode.getLastNode(), text, null, null);
		setNextNodeQuestion(
				(NodeQuestion) currentNode.getLastNode(), nextNode, true);

		NodeQuestion newNextYesNode = newNodeQuestion(nextNode, newLeafAnswer, null, null);
		NodeQuestion newNextNoNode = currentNode;

		setNextNodeQuestion(nextNode, newNextYesNode, true);
		setNextNodeQuestion(nextNode, newNextNoNode, false);
		
		return nextNode;
	}
}
