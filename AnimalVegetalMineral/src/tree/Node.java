package tree;

/*
 * Imported necessary libraries
 */
import java.io.Serializable;

/**
 * Class representing a Node of the tree
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
@SuppressWarnings("serial")
public class Node implements Serializable {

    /**
     * Question of the node
     */
    private String text;
    
    /**
     * Error message to give to the user
     */
    private static final String ERROR_MESSAGE = "AN ERROR HAS OCCURRED";
    
    /**
     * Default constructor of Node
     * @param newText new text to be used as a question
     */
    public Node(final String newText) {
        this.text = (newText == null || newText == "" || newText.equals(new String())
                ? ERROR_MESSAGE : newText);
    }
    
    /**
     * Gets Node's text
     * @return text
     */
    public String getText() {
        return this.text;
    }
}
