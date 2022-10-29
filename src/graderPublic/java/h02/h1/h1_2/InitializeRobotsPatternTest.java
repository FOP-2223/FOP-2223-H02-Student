package h02.h1.h1_2;

import fopbot.Direction;
import fopbot.FieldEntity;
import fopbot.Robot;
import fopbot.World;
import h02.Main;
import h02.Utils;
import h02.h1.H1Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h02.Utils.*;
import static h02.h1.H1Utils.convertArrayOfArrayOfBooleanToString;
import static org.junit.jupiter.api.Assertions.*;


@TestForSubmission
public class InitializeRobotsPatternTest {

    private static final String PATH_TO_CSV = "/h1/h1_2/FittingPatterns.csv";
    private static final String PATH_TO_CSV_2 = "/h1/h1_2/UnfittingPatterns.csv";

    private static final Main main = new Main();


    @BeforeAll
    static void setup() {
        World.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        World.setDelay(0);
    }

    @BeforeEach
    void reset() {
        World.reset();
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testInvocationsOfCountOfRobotsInPattern(String patternAsString) {
        boolean[][] pattern = H1Utils.convertStringToPattern(patternAsString);
        Main mainSpy = Mockito.spy(Main.class);
        mainSpy.initializeRobotsPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);
        Mockito.verify(mainSpy).countRobotsInPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);
    }


    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV_2)
    void testNotFittingPatterns(String patternAsString, int expected) {
        boolean[][] notFittingPattern = H1Utils.convertStringToPattern(patternAsString);
        testNumberOfRobots(notFittingPattern, expected);
        doesNotThrowException(notFittingPattern);
        testCoins(notFittingPattern);
        testDirections(notFittingPattern);
        testCoordinates(notFittingPattern);
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testNumberOfRobotsWithFittingPattern(String patternAsString, int expected) {
        testNumberOfRobots(H1Utils.convertStringToPattern(patternAsString), expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testCoinsWithFittingPattern(String patternAsString) {
        testCoins(H1Utils.convertStringToPattern(patternAsString));
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testDirectionsWithFittingPattern(String patternAsString) {
        testDirections(H1Utils.convertStringToPattern(patternAsString));
    }

    @ParameterizedTest
    @CsvFileSource(resources = PATH_TO_CSV)
    void testCoordinatesWithFittingPattern(String patternAsString) {
        testCoordinates(H1Utils.convertStringToPattern(patternAsString));
    }

    @Test
    void testUseOfCountRobotsInPattern() {

    }

    private void doesNotThrowException(boolean[][] pattern) {
        assertDoesNotThrow(
            () -> main.initializeRobotsPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT),
            Utils.getGeneralInfo("Pattern:\n" + convertArrayOfArrayOfBooleanToString(pattern)) +
                "The method \"initializeRobotsArray\" threw an Exception when processing the pattern above!"
        );
    }

    private void testNumberOfRobots(boolean[][] pattern, int expected) {
        main.initializeRobotsPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);

        int actualNumberOfRobots = World.getGlobalWorld().getAllFieldEntities().size();

        assertEquals(
            expected,
            actualNumberOfRobots,
            Utils.getGeneralInfo("Pattern:\n" + convertArrayOfArrayOfBooleanToString(pattern)) +
                "Expected " + expected + " robots in the world but there were actually " + actualNumberOfRobots + "."
        );
    }

    private void testCoins(boolean[][] pattern) {
        main.initializeRobotsPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);

        int expectedCoins;
        int actualCoins;

        for (FieldEntity robot : World.getGlobalWorld().getAllFieldEntities()) {
            if (robot instanceof Robot) {
                expectedCoins = WORLD_WIDTH - robot.getX();
                actualCoins = ((Robot) robot).getNumberOfCoins();
                assertEquals(
                    expectedCoins,
                    actualCoins,
                    Utils.getGeneralInfo("Pattern:\n" + convertArrayOfArrayOfBooleanToString(pattern)) +
                        "Expected robot " + robot + " to have " + expectedCoins + " coins but it has " + actualCoins + "."
                );
            }
        }
    }

    private void testDirections(boolean[][] pattern) {
        main.initializeRobotsPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);

        Direction expectedDirection = Direction.RIGHT;
        Direction actualDirection;

        for (FieldEntity robot : World.getGlobalWorld().getAllFieldEntities()) {
            if (robot instanceof Robot) {
                actualDirection = ((Robot) robot).getDirection();
                assertEquals(
                    expectedDirection,
                    actualDirection,
                    Utils.getGeneralInfo("Pattern:\n" + convertArrayOfArrayOfBooleanToString(pattern)) +
                        "Expected robot " + robot + " to face " + expectedDirection + " but it actually faces " + actualDirection + "."
                );
            }
        }
    }

    private void testCoordinates(boolean[][] pattern) {
        main.initializeRobotsPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);

        boolean[][] actualPattern = new boolean[WORLD_HEIGHT][WORLD_WIDTH];
        boolean[][] worldSizePattern = H1Utils.getWorldSizeRobotPattern(pattern, WORLD_WIDTH, WORLD_HEIGHT);

        for (FieldEntity robot : World.getGlobalWorld().getAllFieldEntities()) {
            if (robot instanceof Robot) {
                actualPattern[robot.getX()][robot.getY()] = true;
            }
        }

        assertArrayEquals(
            worldSizePattern,
            actualPattern,
            Utils.getGeneralInfo("Pattern:\n" + convertArrayOfArrayOfBooleanToString(pattern)) +
                "Expected the robots to be arranged like this:" +
                convertArrayOfArrayOfBooleanToString(worldSizePattern) +
                "\nBut they are arranged like this:" +
                convertArrayOfArrayOfBooleanToString(actualPattern)
        );
    }

}
