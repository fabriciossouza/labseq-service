package com.github.fabriciossouza;


import com.github.fabriciossouza.domain.SequenceNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.currentTimeMillis;
import static java.math.BigInteger.*;
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
        assertEquals(ZERO, sequenceNumberService.calculateLabSeqNumber(0));
        assertEquals(ONE, sequenceNumberService.calculateLabSeqNumber(1));
        assertEquals(ZERO, sequenceNumberService.calculateLabSeqNumber(2));
        assertEquals(ONE, sequenceNumberService.calculateLabSeqNumber(3));
    }

    @Test
    public void testSubsequentValues() {
        assertEquals(ONE, sequenceNumberService.calculateLabSeqNumber(4));
        assertEquals(ONE, sequenceNumberService.calculateLabSeqNumber(5));
        assertEquals(ONE, sequenceNumberService.calculateLabSeqNumber(6));
        assertEquals(TWO, sequenceNumberService.calculateLabSeqNumber(7));
        assertEquals(TWO, sequenceNumberService.calculateLabSeqNumber(8));
        assertEquals(TWO, sequenceNumberService.calculateLabSeqNumber(9));
        assertEquals(valueOf(3), sequenceNumberService.calculateLabSeqNumber(10));
    }

    @Test
    public void testLargeValue() {
        assertEquals(new BigInteger("182376579"), sequenceNumberService.calculateLabSeqNumber(100));
    }

    @Test
    public void testNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> sequenceNumberService.calculateLabSeqNumber(-1));
    }

    @Test
    public void testNonNegativeValues() {
        for (int i = 0; i < 2000; i++) {
            BigInteger value = sequenceNumberService.calculateLabSeqNumber(i);
            assertTrue(value.compareTo(BigInteger.ZERO) >= 0, "All values in the sequence should be non-negative");
        }
    }

    @Test
    public void testNotStrictlyIncreasingOrDecreasing() {
        for (int i = 1; i < 100; i++) {
            BigInteger current = sequenceNumberService.calculateLabSeqNumber(i);
            BigInteger previous = sequenceNumberService.calculateLabSeqNumber(i - 1);
            assertTrue(!current.equals(previous.add(BigInteger.ONE)) || !current.equals(previous.subtract(BigInteger.ONE)), "Sequence should not be strictly increasing or decreasing");
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
                BigInteger result = sequenceNumberService.calculateLabSeqNumber(index);
                assertEquals(new BigInteger("2"), result);
                latch.countDown();
            });
        }

        latch.await(5, SECONDS);

        executorService.shutdown();
    }

}