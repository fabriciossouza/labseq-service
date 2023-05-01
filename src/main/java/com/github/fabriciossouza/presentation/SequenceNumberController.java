package com.github.fabriciossouza.presentation;


import com.github.fabriciossouza.application.SequenceNumberApplication;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;

import static jakarta.ws.rs.core.Response.ok;

@Path("/labseq")
public class SequenceNumberController implements SequenceNumberApi {
    @Inject
    private SequenceNumberApplication sequenceNumberApplication;

    @Override
    public Response getSequenceNumber(int index) {
        BigInteger number = sequenceNumberApplication.getSequenceNumber(index);
        return ok(number)
                .build();
    }
}
