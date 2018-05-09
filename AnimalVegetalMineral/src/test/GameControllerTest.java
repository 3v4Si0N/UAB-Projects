package test;

import static org.junit.Assert.*;
import org.junit.Test;
import controller.GameController;

public class GameControllerTest {

    @Test
    public void testGetInstance() {
        GameController result = GameController.getInstance();
        GameController expected = GameController.getInstance();
        assertEquals(expected, result);
    }

}
