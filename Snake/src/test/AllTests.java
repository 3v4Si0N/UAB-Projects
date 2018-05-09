package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all the tests
 * @author Ramon Guimerà, Héctor De Armas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
    BodyNodeTest.class,
    FoodTest.class,
    SnakeTest.class,
    KeyEventsTest.class,
    ScreenTest.class})
public class AllTests {
    /**
     * Eclipse:
     * Right click here -> Run as... -> Run configurations... -> Select run all tests in this project 
     */
}
