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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sathyabodh.company.domain.Company;
import com.sathyabodh.company.service.PersistanceService;

@Named
@Path("company")
public class CompanyResource {

	@Inject
	private PersistanceService service ;
	
	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompany(@PathParam("name") String name){
		return service.getCompany(name);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCompany(Company company) throws URISyntaxException{
		service.createCompany(company);
		URI newURI = new URI("/company/v1/company/" + company.getName());
		return Response.status(Status.CREATED).entity("Company Created").location(newURI).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCompany(Company company){
		service.updateCompany(company);
		return Response.status(Status.OK).entity("Company Updated").build();
	}
	
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> list(@QueryParam("q") String name){
		return service.searchCompany(name);
	}
}
