package com.sathyabodh.company.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/v1")
public class CompanyResourceConfig extends ResourceConfig{

	public CompanyResourceConfig() {
		register(JacksonFeature.class);
		packages("com.sathyabodh.company.rest");
	}
}
