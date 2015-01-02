package com.sathyabodh.company.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class CompanyApplication extends ResourceConfig{

	public CompanyApplication() {
		register(JacksonFeature.class);
		packages("com.sathyabodh.company.rest");
	}
}
