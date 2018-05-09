package test;

import static org.junit.Assert.*;
import org.junit.Test;

import tree.Node;
import tree.NodeQuestion;

public class NodeQuestionTest {
    
    @Test
    public void testGetLastNode() {
        Node lastNode = new Node("");
        NodeQuestion resultNode = new NodeQuestion(lastNode, "", null, null);
        Node result = resultNode.getLastNode();
        assertTrue(result.equals(lastNode));
        
        NodeQuestion resultNode2 = new NodeQuestion(null, "", null, null);
        Node result2 = resultNode2.getLastNode();
        assertNull(result2);
    }
    
    @Test
    public void testSetLastNode() {
        NodeQuestion defaultNode = new NodeQuestion(null, "", null, null);
        Node lastNode = new Node("");
        boolean result = defaultNode.setLastNode(lastNode);
        assertTrue(result);
        
        NodeQuestion defaultNode2 = new NodeQuestion(null, "", null, null);
        Node lastNode2 = null;
        boolean result2 = defaultNode2.setLastNode(lastNode2);
        assertFalse(result2);
    }
	
	@Test
	public void testGetNextYesNode() {
	    NodeQuestion fatherNode = new NodeQuestion(null, "", null, null);
        NodeQuestion yesSonNode = new NodeQuestion(fatherNode, "", null, null);
        NodeQuestion noSonNode  = new NodeQuestion(fatherNode, "", null, null);
        fatherNode.setNextNodes(yesSonNode, noSonNode);
        assertEquals(yesSonNode, fatherNode.getNextYesNode());
        
        NodeQuestion fatherNode2 = new NodeQuestion(null, "", null, null);
        assertNull(fatherNode2.getNextYesNode());
	}

	@Test
    public void testGetNextNoNode() {
	    NodeQuestion fatherNode = new NodeQuestion(null, "", null, null);
        NodeQuestion yesSonNode = new NodeQuestion(fatherNode, "", null, null);
        NodeQuestion noSonNode  = new NodeQuestion(fatherNode, "", null, null);
        fatherNode.setNextNodes(yesSonNode, noSonNode);
        assertEquals(noSonNode, fatherNode.getNextNoNode());
        
        NodeQuestion fatherNode2 = new NodeQuestion(null, "", null, null);
        assertNull(fatherNode2.getNextNoNode());
    }
	
	@Test
	public void testSetNextNodes() {
	    NodeQuestion fatherNode = new NodeQuestion(null, "", null, null);
	    NodeQuestion yesSonNode = new NodeQuestion(fatherNode, "", null, null);
	    NodeQuestion noSonNode  = new NodeQuestion(fatherNode, "", null, null);
	    boolean result = fatherNode.setNextNodes(yesSonNode, noSonNode);
	    assertTrue(result);
	    
	    NodeQuestion fatherNode2 = new NodeQuestion(null, "", null, null);
        boolean result2 = fatherNode2.setNextNodes(null, null);
        assertFalse(result2);
	}
	
	@Test
	public void testSetNextYesNode() {
	    NodeQuestion fatherNode = new NodeQuestion(null, "", null, null);
        NodeQuestion yesSonNode = new NodeQuestion(fatherNode, "", null, null);
        boolean result = fatherNode.setNextYesNode(yesSonNode);
        assertTrue(result);
        
        NodeQuestion fatherNode2 = new NodeQuestion(null, "", null, null);
        boolean result2 = fatherNode2.setNextYesNode(null);
        assertFalse(result2);
	}
	
	@Test
    public void testSetNextNoNode() {
        NodeQuestion fatherNode = new NodeQuestion(null, "", null, null);
        NodeQuestion noSonNode = new NodeQuestion(fatherNode, "", null, null);
        boolean result = fatherNode.setNextYesNode(noSonNode);
        assertTrue(result);
        
        NodeQuestion fatherNode2 = new NodeQuestion(null, "", null, null);
        boolean result2 = fatherNode2.setNextYesNode(null);
        assertFalse(result2);
    }
}
