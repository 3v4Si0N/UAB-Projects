package tree;

/**
 * Node of the tree representing a default question
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
@SuppressWarnings("serial")
public class NodeQuestion extends Node {

    /**
     * Last node, which is last question asked
     */
    private Node lastNode;
    
    /**
     * Next node if the user answers yes
     */
	private NodeQuestion nextYesNode;
	
	/**
	 * Next node if the user answers no
	 */
	private NodeQuestion nextNoNode;

	/**
	 * Default constructor
	 * @param newLastNode last question asked
	 * @param newText question of this node
	 * @param newNextYesNode next node if user answers yes
	 * @param newNextNoNode next node if user answers no
	 */
	public NodeQuestion(
	        final Node newLastNode,
	        final String newText,
	        final NodeQuestion newNextYesNode,
	        final NodeQuestion newNextNoNode) {

        super(newText);
        this.lastNode    = newLastNode;
		this.nextYesNode = newNextYesNode;
		this.nextNoNode  = newNextNoNode;
	}
	
	/**
	 * Sets last node
	 * @param newLastNode new Node to be set
	 * @return returns true if lastNode can be set, false otherwise
	 */
    public boolean setLastNode(final Node newLastNode) {
        if (newLastNode != null) {
            this.lastNode = newLastNode;
            return true;
        }
        return false;
    }
    
    /**
     * Gets last node
     * @return last node
     */
    public Node getLastNode() {
        return this.lastNode;
    }
	
    /**
     * Gets next yes node
     * @return next yes node
     */
	public NodeQuestion getNextYesNode() {
	    return this.nextYesNode;
	}
	
	/**
	 * Gets next no node
	 * @return next no node
	 */
	public NodeQuestion getNextNoNode() {
	    return this.nextNoNode;
	}
	
	/**
	 * Sets both next nodes
	 * @param newNextYesNode new next yes node
	 * @param newNextNoNode new next no node
	 * @return true if both nodes are not null, false otherwise
	 */
	public boolean setNextNodes(
	        final NodeQuestion newNextYesNode,
            final NodeQuestion newNextNoNode) {

	    if (newNextYesNode != null && newNextNoNode != null) {
	        this.nextYesNode = newNextYesNode;
	        this.nextNoNode  = newNextNoNode;
	        return true;
	    }
        return false;
	}
	
	/**
	 * Sets a new next yes node
	 * @param newNextYesNode new next yes node
	 * @return true if the new next node is not null, false otherwise
	 */
	public boolean setNextYesNode(final NodeQuestion newNextYesNode) {
	    if (newNextYesNode != null) {
	        this.nextYesNode = newNextYesNode;
	        return true;
	    }
	    return false;
	}
	
	/**
	 * Sets a new next no node
	 * @param newNextNoNode new next no node
	 * @return true if the new next node is not null, false otherwise
	 */
	public boolean setNextNoNode(final NodeQuestion newNextNoNode) {
        if (newNextNoNode != null) {
            this.nextNoNode = newNextNoNode;
            return true;
        }
        return false;
    }
}
