package com.sathyabodh.company.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ObjectNotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7620654767437989728L;
	
	public ObjectNotFoundException(String message){
		super(Response.status(Status.NOT_FOUND).entity(message).build());
	}
	
	public ObjectNotFoundException(){
		super(Response.status(Status.NOT_FOUND).entity("Object does not exists").build());
	}
}
