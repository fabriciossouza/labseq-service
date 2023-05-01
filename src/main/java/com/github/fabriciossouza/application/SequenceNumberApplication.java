package com.github.fabriciossouza.application;

import com.github.fabriciossouza.domain.SequenceNumberService;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;

@ApplicationScoped
public class SequenceNumberApplication {

    private SequenceNumberService sequenceNumberService;
    public SequenceNumberApplication(SequenceNumberService sequenceNumberService) {
        this.sequenceNumberService = sequenceNumberService;
    }
    public BigInteger getSequenceNumber(Integer number) {
        return sequenceNumberService.calculateLabSeqNumber(number);
    }

}
