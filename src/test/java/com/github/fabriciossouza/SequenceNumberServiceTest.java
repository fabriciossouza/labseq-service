package com.github.fabriciossouza;


import com.github.fabriciossouza.domain.SequenceNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.*;

public class SequenceNumberServiceTest {
    private SequenceNumberService sequenceNumberService;
    @BeforeEach
    public void setUp() {
        sequenceNumberService = new SequenceNumberService();
    }

    @Test
    public void testInitialValues() {
        assertEquals(0, sequenceNumberService.get(0));
        assertEquals(1, sequenceNumberService.get(1));
        assertEquals(0, sequenceNumberService.get(2));
        assertEquals(1, sequenceNumberService.get(3));
    }

    @Test
    public void testSubsequentValues() {
        assertEquals(1, sequenceNumberService.get(4));
        assertEquals(1, sequenceNumberService.get(5));
        assertEquals(1, sequenceNumberService.get(6));
        assertEquals(2, sequenceNumberService.get(7));
        assertEquals(2, sequenceNumberService.get(8));
        assertEquals(2, sequenceNumberService.get(9));
        assertEquals(3, sequenceNumberService.get(10));
    }

    @Test
    public void testNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> sequenceNumberService.get(-1));
    }

    @Test
    public void testNonNegativeValues() {
        for (int i = 0; i < 100; i++) {
            long value = sequenceNumberService.get(i);
            assertTrue(value >= 0, "All values in the sequence should be non-negative");
        }
    }

    @Test
    public void testNotStrictlyIncreasingOrDecreasing() {
        for (int i = 1; i < 100; i++) {
            long current = sequenceNumberService.get(i);
            long previous = sequenceNumberService.get(i - 1);
            assertTrue(current != previous + 1 || current != previous - 1, "Sequence should not be strictly increasing or decreasing");
        }
    }

    @Test
    public void testPerformance() {
        long startTime = currentTimeMillis();
        sequenceNumberService.get(10000);
        long elapsedTime = currentTimeMillis() - startTime;

        assertTrue(elapsedTime <= 10000, "Calculation time should be under 10 seconds");
    }
}