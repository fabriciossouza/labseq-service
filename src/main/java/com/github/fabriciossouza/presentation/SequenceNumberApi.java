package com.github.fabriciossouza.presentation;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface SequenceNumberApi {

    @GET
    @Path("/{number}")
    @Operation(summary = "Get labseq value at position number")
    @APIResponse(
            responseCode = "200",
            description = "The labseq value at position number",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    long getSequenceNumber(@Parameter(description = "The position number in the labseq sequence", required = true)
                           @PathParam("number") int number);
}