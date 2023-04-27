package com.github.fabriciossouza.application;

import com.github.fabriciossouza.domain.SequenceNumberService;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SequenceNumberApplication {

    private SequenceNumberService sequenceNumberService;
    public SequenceNumberApplication(SequenceNumberService sequenceNumberService) {
        this.sequenceNumberService = sequenceNumberService;
    }
    public long getSequenceNumber(Integer number) {
        return sequenceNumberService.get(number);
    }
}
