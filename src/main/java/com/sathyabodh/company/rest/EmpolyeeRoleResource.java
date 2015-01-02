package com.sathyabodh.company.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sathyabodh.company.domain.EmployeeRole;
import com.sathyabodh.company.service.PersistanceService;

@Path("employeeroles")
@Named
public class EmpolyeeRoleResource {
	
	@Inject
	private PersistanceService service ;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployeeRole(EmployeeRole role) throws URISyntaxException{
		service.createEmployeeRole(role);
		return Response.created(new URI(role.getName())).entity("New resource is created").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployeeRole(EmployeeRole role){
		service.updateEmployeeRole(role);
		return Response.ok().entity("Updated successfully").build();
	}
	
	@GET
	@Path("/{role}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeRole getEmployeeRole(String role){
		return service.getEmployeeRole(role);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmployeeRole> getAll(){
		return service.findAllEmployeeRoles();
	}
}
