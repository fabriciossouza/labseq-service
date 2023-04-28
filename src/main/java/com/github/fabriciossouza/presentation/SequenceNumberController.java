package com.github.fabriciossouza.presentation;


import com.github.fabriciossouza.application.SequenceNumberApplication;
import com.github.fabriciossouza.exceptions.InvalidNumberException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/labseq")
public class SequenceNumberController implements SequenceNumberApi {
    @Inject
    private SequenceNumberApplication sequenceNumberApplication;

    @Override
    public long getSequenceNumber(int number) {
        if (number < 0) {
            throw new InvalidNumberException("Illegal argument: number must be non-negative");
        }
        return sequenceNumberApplication.getSequenceNumber(number);
    }
}
