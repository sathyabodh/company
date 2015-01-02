package com.sathyabodh.company.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sathyabodh.company.domain.Organization;
import com.sathyabodh.company.service.PersistanceService;

@Named
@Path("company/{companyName}/organization")
public class OrganizationResource {

	@Inject
	private PersistanceService service ;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@PathParam("companyName")String companyName,Organization org){
		org.setCompanyName(companyName);
		service.createOrganization(org);
		return Response.status(Status.CREATED).entity("Organization object is created").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("companyName")String companyName, Organization org){
		service.updateOrganization(org);
		return Response.status(Status.CREATED).entity("Organization object is updated").build();
	}
	
	@Path("/{orgName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Organization getOrganization(@PathParam("companyName") String companyName, @PathParam("orgName") String organization){
		return service.getOrganization(organization, companyName);
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Organization> list(@QueryParam("name") String name, @PathParam("companyName") String companyName){
//		return service.searchOrganization(name, companyName);
//	}
} 
