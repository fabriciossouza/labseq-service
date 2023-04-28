package com.github.fabriciossouza.mappers;

import com.github.fabriciossouza.exceptions.InvalidNumberException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class InvalidNumberExceptionMapper implements ExceptionMapper<InvalidNumberException> {
    @Override
    public Response toResponse(InvalidNumberException exception) {
        return Response.status(BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}