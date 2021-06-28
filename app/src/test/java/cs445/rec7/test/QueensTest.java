package cs445.rec7.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cs445.rec7.Queens;

/**
 * A class that provides sample tests of the 8 Queens Program
 * @author William C. Garrison III
 * @author Brian T. Nixon
 * @author Norhan Abbas
 */
public class QueensTest {

    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void teardown() {
    }

    @Test
    @DisplayName("Test isFullSolution()")
    void testIsFullSolution() {
        int[][] fullSolutions = new int[][] {
            {2, 4, 6, 8, 3, 1, 7, 5},
            {1, 7, 4, 6, 8, 2, 5, 3},
        };

        int[][] notFullSolutions = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 4, 6, 8, 3, 5, 7, 0},
            {0, 2, 0, 0, 2, 0, 0, 0},
            {0, 2, 0, 0, 1, 0, 0, 2},
            {2, 4, 6, 8, 3, 1, 5, 0},
            {0, 5, 0, 0, 2, 0, 0, 0},
        };

        for (int[] test : fullSolutions) {
            assertTrue(Queens.isFullSolution(test), "Failed to detect a full solution");
        }

        for (int[] test : notFullSolutions) {
            assertFalse(Queens.isFullSolution(test), "Failed to detect a full solution");
        }
    }

    @Test
    @DisplayName("Test reject()")
    void testReject() {
        int[][] notRejected = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 4, 6, 8, 3, 5, 7, 0},
            {2, 4, 6, 8, 3, 1, 7, 5},
        };

        int[][] rejected = new int[][] {
            {0, 2, 0, 0, 2, 0, 0, 0},
            {0, 2, 0, 0, 1, 0, 0, 2},
            {2, 4, 6, 8, 3, 1, 5, 0},
            {0, 5, 0, 0, 2, 0, 0, 0},
        };

        for (int[] test : notRejected) {
            assertFalse(Queens.reject(test), "Rejected a valid partial solution");
        }

        for (int[] test : rejected) {
            assertTrue(Queens.reject(test), "Failed to reject an incorrect partial solution");
        }
    }

    @Test
    @DisplayName("Test next()")
    void testNext() {
        int[][] noNext = new int[][] {
            {1, 3, 5, 7, 2, 4, 6, 8},
            {2, 4, 6, 8, 0, 0, 0, 0},
        };

        int[][] next = new int[][] {
            {2, 4, 6, 8, 3, 1, 7, 5},
            {1, 7, 4, 6, 8, 2, 5, 3},
            {1, 7, 4, 6, 8, 2, 5, 2},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {2, 4, 6, 8, 3, 5, 7, 0},
            {2, 4, 6, 8, 3, 1, 5, 0},
        };

        for (int[] test : noNext) {
            assertNull(Queens.next(test), "Failed to return null for for next queen placement");
        }

        int[][] results = new int[][] {
            Queens.next(next[0]),
            Queens.next(next[1]),
            Queens.next(next[2]),
            Queens.next(next[3]),
            Queens.next(next[4]),
            Queens.next(next[5])
        };

        for (int[] test : results) {
            assertNotNull(test, "Should have placed next queen instead of returning null");
        }

        // Test next placement
        assertEquals(6, results[0][7], "next() did not move most recent queen");
        assertEquals(4, results[1][7], "next() did not move most recent queen");
        assertEquals(3, results[2][7], "next() did not move most recent queen");
        assertEquals(2, results[3][0], "next() did not move most recent queen");
        assertEquals(8, results[4][6], "next() did not move most recent queen");
        assertEquals(6, results[5][6], "next() did not move most recent queen");
    }

    @Test
    @DisplayName("Test extend()")
    void testExtend() {
        int[][] noExtend = new int[][] {
            {2, 4, 6, 8, 3, 1, 7, 5},
            {1, 7, 4, 6, 8, 2, 5, 3},
            {1, 7, 4, 6, 8, 2, 5, 2},
            {1, 3, 5, 7, 2, 4, 6, 8},
        };

        int[][] extend = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 4, 6, 8, 3, 5, 7, 0},
            {2, 4, 6, 8, 0, 0, 0, 0},
            {2, 4, 6, 8, 3, 1, 5, 0},
        };

        for (int[] test : noExtend) {
            assertNull(Queens.extend(test), "Failed to detect non-extendable partial solution");
        }

        int[][] results = new int[][] {
            Queens.extend(extend[0]),
            Queens.extend(extend[1]),
            Queens.extend(extend[2]),
            Queens.extend(extend[3])
        };

        for (int[] test : results) {
            assertNotNull(test, "Failed to extend partial solution");
        }

        // check Queen Placement
        assertEquals(1, results[0][0], "extend() placed queen in incorrect position");
        assertEquals(1, results[1][7], "extend() placed queen in incorrect position");
        assertEquals(1, results[2][4], "extend() placed queen in incorrect position");
        assertEquals(1, results[3][7], "extend() placed queen in incorrect position");
    }
}
