package com.sathyabodh.company.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ObjectAlreadyExists extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 185796246574869207L;

	public ObjectAlreadyExists() {
		super(Response.status(Status.CONFLICT).entity("Object already exists").build());
	}
}
