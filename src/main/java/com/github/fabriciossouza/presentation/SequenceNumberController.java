package com.github.fabriciossouza.presentation;


import com.github.fabriciossouza.application.SequenceNumberApplication;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/labseq")
public class SequenceNumberController {

    @Inject
    SequenceNumberApplication sequenceNumberApplication;
    @GET
    @Path("/{number}")
    public long getSequenceNumber(@PathParam("number") int number) {
        return sequenceNumberApplication.getSequenceNumber(number);
    }
}
