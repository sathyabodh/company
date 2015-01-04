package com.sathyabodh.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class CompanyRestApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(CompanyRestApplication.class);
	}
	
	public static void main(String[] args) {
		
//		new CompanyRestApplication().configure(new SpringApplicationBuilder(CompanyRestApplication.class)).run(args);
		SpringApplication.run(CompanyRestApplication.class, args);
	}
}
