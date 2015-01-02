package com.sathyabodh.company.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.joda.time.DateTime;

@Provider
public class ResponseHeaderAdditionFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("X-Powered-By", "Sathyabodh");
		responseContext.getHeaders().add(HttpHeaders.EXPIRES, DateTime.now());
		responseContext.getHeaders().add(HttpHeaders.CACHE_CONTROL,false);
	}
	
}
