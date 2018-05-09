package test;

import static org.junit.Assert.*;
import org.junit.Test;

import tree.Node;
import tree.NodeQuestion;
import tree.NodeRoot;

public class NodeRootTest {
    
    @Test
	public void testSetAnimalBranch() {
        NodeRoot rootNode = new NodeRoot("", null, null, null);
        NodeQuestion animalBranch = new NodeQuestion(rootNode, "¿Es un pajaro?", null, null);
        
        boolean result = rootNode.setAnimalBranch(animalBranch);
        assertTrue(result);
        
        boolean result2 = rootNode.setAnimalBranch(null);
        assertFalse(result2);
	}
    
    @Test
	public void testSetVegetableBranch() {
		NodeRoot rootNode = new NodeRoot("", null, null, null);
        NodeQuestion vegetableBranch = new NodeQuestion(rootNode, "¿Es un vegetal?", null, null);
        
        boolean result = rootNode.setVegetableBranch(vegetableBranch);
        assertTrue(result);
        
        boolean result2 = rootNode.setAnimalBranch(null);
        assertFalse(result2);
	}
    
    @Test
	public void testSetMineralBranch() {
		NodeRoot rootNode = new NodeRoot("", null, null, null);
        NodeQuestion mineralBranch = new NodeQuestion(rootNode, "¿Es un mineral?", null, null);
        
        boolean result = rootNode.setAnimalBranch(mineralBranch);
        assertTrue(result);
        
        boolean result2 = rootNode.setAnimalBranch(null);
        assertFalse(result2);
	}
    
    @Test
    public void testGetAnimalBranch() {
        NodeQuestion animalBranch = new NodeQuestion(null, "", null, null);
        NodeRoot rootNode = new NodeRoot("", animalBranch, null, null);
        Node result = rootNode.getAnimalBranch();
        assertEquals(animalBranch, result);
        
    }
    
    @Test
    public void testGetVegetableBranch() {
        NodeQuestion vegetableBranch = new NodeQuestion(null, "", null, null);
        NodeRoot rootNode = new NodeRoot("", null, vegetableBranch, null);
        Node result = rootNode.getVegetableBranch();
        assertEquals(vegetableBranch, result);
    }
    
    @Test
    public void testGetMineralBranch() {
        NodeQuestion mineralBranch = new NodeQuestion(null, "", null, null);
        NodeRoot rootNode = new NodeRoot("", null, null, mineralBranch);
        Node result = rootNode.getMineralBranch();
        assertEquals(mineralBranch, result);
    }
}
