package com.github.fabriciossouza.application;

import com.github.fabriciossouza.domain.SequenceNumberService;

public class SequenceNumberApplication {

    private SequenceNumberService sequenceNumberService;
    public SequenceNumberApplication(SequenceNumberService sequenceNumberService) {
        this.sequenceNumberService = sequenceNumberService;
    }
    public long getSequenceNumber(Integer number) {
        return sequenceNumberService.get(number);
    }
}
