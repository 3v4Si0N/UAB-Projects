package test;

import static org.junit.Assert.*;
import org.junit.Test;
import controller.TreeController;
import tree.Node;
import tree.NodeQuestion;
import tree.NodeRoot;

public class TreeControllerTest {

	@Test
	public void testGetInstance() {
		TreeController instance = TreeController.getInstance();
		TreeController instanceExpected = TreeController.getInstance();
		assertEquals(instanceExpected, instance);
	}

	@Test
	public void testNewNodeQuestion() {
		TreeController instance = TreeController.getInstance();
		Node root = instance.newNodeRoot("this is root", null, null, null);
		String text = "question";
		
		NodeQuestion question = instance.newNodeQuestion(root, text, null, null);
		assertEquals(NodeQuestion.class, question.getClass());
		assertEquals(text, question.getText());
		
		NodeQuestion question1 = instance.newNodeQuestion(null, "", null, null);
		assertNull(question1);
		
		NodeQuestion question2 = instance.newNodeQuestion(root, null, null, null);
        assertNull(question2);
        
        NodeQuestion question3 = instance.newNodeQuestion(root, "", null, null);
        assertNull(question3);
        
        NodeQuestion question4 = instance.newNodeQuestion(root, new String(), null, null);
        assertNull(question4);
	}

	@Test
	public void testNewNodeRoot() {
	    String text = "this is root";
	    
	    Node root = TreeController.getInstance()
	            .newNodeRoot(text, null, null, null);
	    assertEquals(NodeRoot.class, root.getClass());
	    assertEquals(text, root.getText());
	    
	    Node root2 = TreeController.getInstance()
                .newNodeRoot(null, null, null, null);
        assertNull(root2);
        
        Node root3 = TreeController.getInstance()
                .newNodeRoot("", null, null, null);
        assertNull(root3);
        
        Node root4 = TreeController.getInstance()
                .newNodeRoot(new String(), null, null, null);
        assertNull(root4);
	}

	@Test
	public void testCreateEmptyTree() {
		TreeController instance = TreeController.getInstance();
		NodeRoot root = instance.createEmptyTree();
		
		assertEquals("Animal, Vegetable or Mineral", root.getText());
		assertEquals("Es un pajaro", root.getAnimalBranch().getText());
		assertEquals("Es verde", root.getVegetableBranch().getText());
		assertEquals("Es brillante", root.getMineralBranch().getText());
		
		assertNotNull(root.getAnimalBranch());
		assertNotNull(root.getVegetableBranch());
		assertNotNull(root.getMineralBranch());
		
		assertEquals("Animal, Vegetable or Mineral",
				root.getAnimalBranch().getLastNode().getText());
		assertEquals("Animal, Vegetable or Mineral",
				root.getVegetableBranch().getLastNode().getText());
		assertEquals("Animal, Vegetable or Mineral",
				root.getMineralBranch().getLastNode().getText());
		
		
		assertNotNull(root.getAnimalBranch().getNextYesNode());
		assertNotNull(root.getAnimalBranch().getNextNoNode());
		assertNotNull(root.getVegetableBranch().getNextYesNode());
		assertNotNull(root.getVegetableBranch().getNextNoNode());
		assertNotNull(root.getMineralBranch().getNextYesNode());
		assertNotNull(root.getMineralBranch().getNextNoNode());
		
		assertEquals("Pensabas en un loro", 
				root.getAnimalBranch().getNextYesNode().getText());
		assertEquals("Pensabas en lechuga", 
				root.getVegetableBranch().getNextYesNode().getText());
		assertEquals("Pensabas en oro", 
				root.getMineralBranch().getNextYesNode().getText());
		
		assertEquals("Pensabas en un perro", 
				root.getAnimalBranch().getNextNoNode().getText());
		assertEquals("Pensabas en un tomate", 
				root.getVegetableBranch().getNextNoNode().getText());
		assertEquals("Pensabas en cobre", 
				root.getMineralBranch().getNextNoNode().getText());
	}
	
	@Test
	public void testSetNextNodeQuestion() {
	    TreeController instance = TreeController.getInstance();
	    NodeRoot root = instance.createEmptyTree();
	    NodeQuestion defaultQuestion = root.getAnimalBranch();
	    NodeQuestion newQuestion = instance.newNodeQuestion(defaultQuestion, "new quest", null, null);
	    
	    boolean result1 = instance.setNextNodeQuestion(newQuestion, defaultQuestion, true);
	    assertTrue(result1);
	    
	    boolean result2 = instance.setNextNodeQuestion(newQuestion, defaultQuestion, false);
	    assertTrue(result2);
	    
	    boolean result3 = instance.setNextNodeQuestion(null, null, true);
	    assertFalse(result3);

	    boolean result4 = instance.setNextNodeQuestion(newQuestion, null, true);
	    assertFalse(result4);

	    boolean result5 = instance.setNextNodeQuestion(null, defaultQuestion, true);
	    assertFalse(result5);
	}
	
	@Test
	public void testIsNextYesNodeNull() {
	    TreeController instance = TreeController.getInstance();
        NodeRoot root = instance.createEmptyTree();
        NodeQuestion defaultQuestion = root.getAnimalBranch();
        NodeQuestion newQuestion = instance.newNodeQuestion(defaultQuestion, "new quest", null, null);
        
        assertTrue(instance.isNextYesNodeNull(newQuestion));
        
        NodeQuestion newQuestion2 = instance.newNodeQuestion(defaultQuestion, "new quest", defaultQuestion, null);
        assertFalse(instance.isNextYesNodeNull(newQuestion2));
	}
	
	@Test
	public void testAddNewNode() {
		TreeController treeController = TreeController.getInstance();
		
		// Animal test case
		NodeRoot root = treeController.createEmptyTree();
		Node currentNode = root.getAnimalBranch();
		while (((NodeQuestion) currentNode).getNextNoNode() != null) {
			currentNode = ((NodeQuestion) currentNode).getNextNoNode();
		}
		
		String questionText = "Pensabas en un perro";
		assertEquals(questionText,currentNode.getText());
		
		String text = "my question";
		String newLeafAnswer = "my answer";
		Node newNode = treeController.addNewNode((NodeQuestion) currentNode, text, newLeafAnswer);
		
		assertEquals(text, newNode.getText());
		assertEquals(newLeafAnswer, ((NodeQuestion) newNode).getNextYesNode().getText());
		assertEquals(questionText, ((NodeQuestion) newNode).getNextNoNode().getText());
		
		// Vegetable test case
		NodeRoot root2 = treeController.createEmptyTree();
		Node currentNode2 = root2.getVegetableBranch();
		while (((NodeQuestion) currentNode2).getNextNoNode() != null) {
			currentNode2 = ((NodeQuestion) currentNode2).getNextNoNode();
		}
		
		String questionText2 = "Pensabas en un tomate";
		assertEquals(questionText2,currentNode2.getText());
		
		String text2 = "my question2";
		String newLeafAnswer2 = "my answer2";
		Node newNode2 = treeController.addNewNode((NodeQuestion) currentNode2, text2, newLeafAnswer2);
		
		assertEquals(text2, newNode2.getText());
		assertEquals(newLeafAnswer2, ((NodeQuestion) newNode2).getNextYesNode().getText());
		assertEquals(questionText2, ((NodeQuestion) newNode2).getNextNoNode().getText());
		
		
		// Mineral test case
		NodeRoot root3 = treeController.createEmptyTree();
		Node currentNode3 = root3.getMineralBranch();
		while (((NodeQuestion) currentNode3).getNextNoNode() != null) {
			currentNode3 = ((NodeQuestion) currentNode3).getNextNoNode();
		}
		
		String questionText3 = "Pensabas en cobre";
		assertEquals(questionText3, currentNode3.getText());
		
		String text3 = "my question3";
		String newLeafAnswer3 = "my answer3";
		Node newNode3 = treeController.addNewNode((NodeQuestion) currentNode3, text3, newLeafAnswer3);
		
		assertEquals(text3, newNode3.getText());
		assertEquals(newLeafAnswer3, ((NodeQuestion) newNode3).getNextYesNode().getText());
		assertEquals(questionText3, ((NodeQuestion) newNode3).getNextNoNode().getText());
		
		
		// currentNode = null case
		Node newNode4 = treeController.addNewNode(null, text, newLeafAnswer);
		assertNull(newNode4);
	}
}
