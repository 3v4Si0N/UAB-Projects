package tree;

/**
 * Node root, represents starting question of the game
 * 
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
@SuppressWarnings("serial")
public class NodeRoot extends Node {

    /**
     * Next question for animal branch
     */
    private NodeQuestion animalBranch;
    
    /**
     * Next question for vegetable branch
     */
    private NodeQuestion vegetableBranch;
    
    /**
     * Next question for mineral branch
     */
    private NodeQuestion mineralBranch;
    
    /**
     * Default constructor for Node root
     * @param newText main question to ask
     * @param firstAnimalQuestion first question for animal
     * @param firstVegetableQuestion first question for vegetable
     * @param firstMineralQuestion first question for mineral
     */
    public NodeRoot(
            final String newText,
            final NodeQuestion firstAnimalQuestion,
            final NodeQuestion firstVegetableQuestion,
            final NodeQuestion firstMineralQuestion) {
        
        super(newText);
        this.animalBranch    = firstAnimalQuestion;
        this.vegetableBranch = firstVegetableQuestion;
        this.mineralBranch   = firstMineralQuestion;
    }
    
    /**
     * Sets the animal branch
     * @param newAnimalBranch first question for animal branch
     * @return true if new first question is not null, false otherwise
     */
    public boolean setAnimalBranch(final NodeQuestion newAnimalBranch) {
        if (newAnimalBranch != null) {
        	this.animalBranch = newAnimalBranch;
	        return true;
	    }
        return false;
    }
    
    /**
     * Sets the vegetable branch
     * @param newVegetableBranch first question for vegetable branch
     * @return true if new first question is not null, false otherwise
     */
    public boolean setVegetableBranch(final NodeQuestion newVegetableBranch) {
        if (newVegetableBranch != null) {
        	this.vegetableBranch = newVegetableBranch;
	        return true;
	    }
        return false;
    }

    /**
     * Sets the mineral branch
     * @param newMineralBranch first question for mineral branch
     * @return true if new first question is not null, false otherwise
     */
    public boolean setMineralBranch(final NodeQuestion newMineralBranch) { 
        if (newMineralBranch != null) {
        	this.mineralBranch = newMineralBranch;
	        return true;
	    }
        return false;
    }

    /**
     * Gets animal branch
     * @return animal branch
     */
    public NodeQuestion getAnimalBranch() {
        return this.animalBranch;
    }

    /**
     * Gets vegetable branch
     * @return vegetable branch
     */
    public NodeQuestion getVegetableBranch() {
        return this.vegetableBranch;
    }

    /**
     * Gets mineral branch
     * @return mineral branch
     */
    public NodeQuestion getMineralBranch() {
        return this.mineralBranch;
    }  
}
