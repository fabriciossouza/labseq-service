package com.github.fabriciossouza;


import com.github.fabriciossouza.domain.SequenceNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
