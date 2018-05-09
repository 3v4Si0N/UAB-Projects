package test;

import static org.junit.Assert.*;
import org.junit.Test;
import controller.SerSaver;
import tree.Node;

public class SerSaverTest {

    @Test
    public void testGetInstance() {
        SerSaver result = SerSaver.getInstance();
        SerSaver expected = SerSaver.getInstance();
        assertEquals(expected, result);
    }
    
    @Test
    public void testSaveAndLoad() {
        SerSaver saver = SerSaver.getInstance();
        Node nodeA = new Node("Result text");
        saver.save(nodeA, "test_save_and_load");
        Node nodeB = (Node) saver.load("test_save_and_load");
        assertEquals(nodeB.getText(), nodeA.getText());
    }
}
