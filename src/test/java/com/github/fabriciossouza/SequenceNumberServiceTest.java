package com.github.fabriciossouza;


import com.github.fabriciossouza.domain.SequenceNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

public class SequenceNumberServiceTest {
    private SequenceNumberService sequenceNumberService;
    @BeforeEach
    public void setUp() {
        sequenceNumberService = new SequenceNumberService();
    }

    @Test
    public void testInitialValues() {
        assertEquals(0, sequenceNumberService.calculateLabSeqNumber(0));
        assertEquals(1, sequenceNumberService.calculateLabSeqNumber(1));
        assertEquals(0, sequenceNumberService.calculateLabSeqNumber(2));
        assertEquals(1, sequenceNumberService.calculateLabSeqNumber(3));
    }

    @Test
    public void testSubsequentValues() {
        assertEquals(1, sequenceNumberService.calculateLabSeqNumber(4));
        assertEquals(1, sequenceNumberService.calculateLabSeqNumber(5));
        assertEquals(1, sequenceNumberService.calculateLabSeqNumber(6));
        assertEquals(2, sequenceNumberService.calculateLabSeqNumber(7));
        assertEquals(2, sequenceNumberService.calculateLabSeqNumber(8));
        assertEquals(2, sequenceNumberService.calculateLabSeqNumber(9));
        assertEquals(3, sequenceNumberService.calculateLabSeqNumber(10));
    }

    @Test
    public void testLargeValue() {
        assertEquals(182376579, sequenceNumberService.calculateLabSeqNumber(100));
        assertEquals(9062959782884117635L, sequenceNumberService.calculateLabSeqNumber(10000));
    }

    @Test
    public void testNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> sequenceNumberService.calculateLabSeqNumber(-1));
    }

    @Test
    public void testNonNegativeValues() {
        for (int i = 0; i < 2000 ; i++) {
            long value = sequenceNumberService.calculateLabSeqNumber(i);
            assertTrue(value >= 0, "All values in the sequence should be non-negative");
        }
    }

    @Test
    public void testNotStrictlyIncreasingOrDecreasing() {
        for (int i = 1; i < 100; i++) {
            long current = sequenceNumberService.calculateLabSeqNumber(i);
            long previous = sequenceNumberService.calculateLabSeqNumber(i - 1);
            assertTrue(current != previous + 1 || current != previous - 1, "Sequence should not be strictly increasing or decreasing");
        }
    }

    @Test
    public void testValueGreaterOrEqualHalfIndexValue() {
        for (int i = 2; i < 100; i++) {
            long value = sequenceNumberService.calculateLabSeqNumber(i);
            long halfIndexValue = sequenceNumberService.calculateLabSeqNumber(i / 2);
            assertTrue(value >= halfIndexValue, "Value at index n should be greater than or equal to value at index n/2");
        }
    }

    @Test
    public void testPerformance() {
        long startTime = currentTimeMillis();
        sequenceNumberService.calculateLabSeqNumber(10000);
        long elapsedTime = currentTimeMillis() - startTime;

        assertTrue(elapsedTime <= 10000, "Calculation time should be under 10 seconds");
    }

    @Test
    void testReturnCorrectValueInMultiThreadedEnvironment() throws Exception {
        var sequenceNumberService = new SequenceNumberService();
        int numberOfThreads = 100;
        int index = 100;
        var latch = new CountDownLatch(numberOfThreads);

        var executorService = newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                long result = sequenceNumberService.calculateLabSeqNumber(index);
                assertEquals(2L, result);
                latch.countDown();
            });
        }

        latch.await(5, SECONDS);

        executorService.shutdown();
    }
}