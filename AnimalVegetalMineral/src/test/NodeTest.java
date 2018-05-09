package test;

import static org.junit.Assert.*;
import org.junit.Test;
import tree.Node;

public class NodeTest {
    
    @Test
    public void testGetText() {
        Node newNode = new Node("");
        String expectedQuestion = "AN ERROR HAS OCCURRED";
        assertEquals(expectedQuestion, newNode.getText());
        
        Node newNode2 = new Node("test");
        String expectedQuestion2 = "test";
        assertEquals(expectedQuestion2, newNode2.getText());
        
        Node newNode3 = new Node(null);
        String expectedQuestion3 = "AN ERROR HAS OCCURRED";
        assertEquals(expectedQuestion3, newNode3.getText());
        
        Node newNode4 = new Node(new String());
        String expectedQuestion4 = "AN ERROR HAS OCCURRED";
        assertEquals(expectedQuestion4, newNode4.getText());
    }
}
