package com.sathyabodh.company.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sathyabodh.company.domain.Employee;
import com.sathyabodh.company.service.PersistanceService;

@Named
@Path("company/{companyName}/organization/{orgName}/department/{deptName}/employee")
public class EmployeeResource {

	@Inject
	private PersistanceService service ;
	
	@PathParam("companyName")
	private String cmpName ;
	
	@PathParam("orgName")
	private String orgName;
	
	@PathParam("deptName")
	private String deptName ;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee(Employee emp) throws URISyntaxException{
		service.createEmployee(cmpName, orgName, deptName, emp);
		return Response.created(new URI("/employee/" + emp.getName())).entity("Employee created").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateeEmployee(Employee emp) throws URISyntaxException{
		service.updateEmployee(cmpName, orgName, deptName, emp);
		return Response.created(new URI("/employee/" + emp.getName())).entity("Employee created").build();
	}

	@Path("/{empName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("empName") String empName){
		return service.getEmployee(cmpName, orgName, deptName, empName);
				
	}
	
	@Path("/{empName}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("empName")String empName){
		service.deleteEmployee(cmpName, orgName, deptName, empName);
		return Response.ok().entity("Deleted Successfully").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> findAll(){
		return service.findAllEmployees();
	}
}
