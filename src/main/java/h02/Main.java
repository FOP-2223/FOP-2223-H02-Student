package h02;

import fopbot.Robot;
import fopbot.World;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Main entry point in executing the program.
 */
public class Main {
    // Delay between each action in FopBot-World (world), for example:
    // Waits 1000ms between each .move() call
    public static final int DELAY = 1000;

    // Generates random int between 4 (inclusive) and 10 (exclusive)
    public static int getRandomWorldSize() {
        return 4 + ThreadLocalRandom.current().nextInt(6);
    }

    // Name of file for patterns
    public static final String FILENAME = "ExamplePattern.txt";

    public static void main(String[] args) {
        int numberOfColumns = getRandomWorldSize();
        int numberOfRows = getRandomWorldSize();
        World.setSize(numberOfColumns, numberOfRows);
        World.setDelay(DELAY);
        World.setVisible(true);
        System.out.println("Size of world: " + numberOfColumns + "x" + numberOfRows);

        PatternProvider patternProvider;
        try {
            patternProvider = new PatternProvider(FILENAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean[][] testPattern = patternProvider.getPattern();

        Robot[] allRobots = initializeRobotsPattern(testPattern, numberOfColumns, numberOfRows);

        // TODO: H2.2 - Put your code here


    }

    /**
     * Counts the number of robots in a pattern, given a specified world size.
     *
     * @param pattern           The pattern for the robots.
     * @param numberOfColumns   Number of columns in the world.
     * @param numberOfRows      Number of rows in the world.
     * @return                  Number of robots in the world.
     */
    public static int countRobotsInPattern(boolean[][] pattern, int numberOfColumns, int numberOfRows) {
        return crash(); // TODO: H1.1 - remove if implemented
    }

    /**
     * Initialize allRobots array for given pattern and world size.
     *
     * @param pattern           The pattern for the robots.
     * @param numberOfColumns   Number of columns in world.
     * @param numberOfRows      Number of rows in world.
     * @return                  Correctly initialized allRobots array.
     */
    public static Robot[] initializeRobotsPattern(boolean[][] pattern, int numberOfColumns, int numberOfRows) {
        return crash(); // TODO: H1.2 - remove if implemented
    }

    /**
     * Returns how many of the components of the given robot-array are null.
     *
     * @param allRobots   The Robot-array.
     * @return            True, if array contains robot.
     */
    public static int numberOfNullRobots(Robot[] allRobots) {
        return crash(); // TODO: H3.1 - remove if implemented
    }

    /**
     * Creates an array containing three (pseudo-) random int values from 0 (inclusive) to given parameter (exclusive).
     *
     * @param bound   The upper bound for the int values.
     * @return        The array.
     */
    public static int[] generateThreeDistinctRandomIndices(int bound) {
        return crash(); // TODO: H3.2 - remove if implemented
    }

    /**
     * Sorts the given 3 valued array from lowest to highest.
     *
     * @param array   The array to be sorted.
     */
    public static void sortArray(int[] array) {
        crash(); // TODO: H3.3 - remove if implemented
    }

    /**
     * Swaps three robots in given robot array.
     * Robot at index i will later be at index j.
     * Robot at index j will later be at index k.
     * Robot at index k will later be at index i.
     *
     * @param indices       Array containing indices i, j and k.
     * @param allRobots     Array containing the robots.
     */
    public static void swapRobots(int[] indices, Robot[] allRobots) {
        crash(); // TODO: H3.4 - remove if implemented
    }

    /**
     * Reduces the given robot array by the set amount.
     * @param robots    The array to be reduced.
     * @param reduceBy  The number of indices that are reduced.
     * @return          The reduced array.
     */
    public static Robot[] reduceRobotArray(Robot[] robots, int reduceBy) {
        return crash(); // TODO: H3.5 - remove if implemented
    }

    /**
     * Lets all robots in the given array walk to the right while also putting down coins.
     * If robots leave the world they are set to null.
     * After the steps are made, if more than three robots exist, three of them change their index.
     * If more than 2 components of the array are null, the array is reduced by the amount of null components.
     *
     * @param allRobots   Array containing all the robots.
     */
    public static void letRobotsMarch(Robot[] allRobots) {
        crash(); // TODO: H4 - remove if implemented
    }
}
