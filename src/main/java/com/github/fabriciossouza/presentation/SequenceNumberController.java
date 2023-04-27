package com.github.fabriciossouza.presentation;


import com.github.fabriciossouza.application.SequenceNumberApplication;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/labseq")
public class SequenceNumberController implements SequenceNumberApi {
    @Inject
    private SequenceNumberApplication sequenceNumberApplication;

    @Override
    public long getSequenceNumber(int number) {
        return sequenceNumberApplication.getSequenceNumber(number);
    }
}
