package com.github.fabriciossouza.presentation;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import static java.lang.Integer.MAX_VALUE;

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
                           @Max(value = MAX_VALUE, message = "The number must be within the valid integer range")
                           @Min(value = 0, message = "The index may be any non-negative integer number")
                           @PathParam("number") int number);
}
