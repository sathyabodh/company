package com.sathyabodh.company.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sathyabodh.company.domain.Employee;

public class SearchResource {

	private final static String PAGE="0";
	private final static String PAGE_SIZE="10";
	
	@Path("employee")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees(@QueryParam("q") String query, @DefaultValue(PAGE)@QueryParam("page") int page, @DefaultValue(PAGE_SIZE)@QueryParam("page-size") int pageSize, @DefaultValue("name")@QueryParam("order-by") String order){
		return null;
	}
}
