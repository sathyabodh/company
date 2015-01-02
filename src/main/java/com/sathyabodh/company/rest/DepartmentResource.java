package com.sathyabodh.company.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sathyabodh.company.domain.Department;
import com.sathyabodh.company.service.PersistanceService;

@Named
@Path("/company/{companyName}/organization/{orgName}/department")
public class DepartmentResource {

	@Inject
	private PersistanceService service ;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDepartment(@PathParam("companyName") String company, @PathParam("orgName") String orgName, Department dept){
		service.createDepartment(company, orgName, dept);
		return Response.status(Status.CREATED).entity("Department created").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response udate(@PathParam("companyName") String company, @PathParam("orgName") String orgName, Department dept){
		service.updateDepartment(company, orgName, dept);
		return Response.status(Status.OK).entity("Department updated").build();
	}
	
	@Path("/{deptName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Department getDepartment(@PathParam("companyName") String company, @PathParam("orgName") String orgName,@PathParam("deptName") String deptName){
		return service.getDepartment(company, orgName, deptName);
	}
}
