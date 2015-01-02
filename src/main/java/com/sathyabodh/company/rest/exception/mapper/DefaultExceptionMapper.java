package com.sathyabodh.company.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		exception.printStackTrace();
		return Response.serverError().entity("Internal server error occured. Please try again").build();
	}

}
