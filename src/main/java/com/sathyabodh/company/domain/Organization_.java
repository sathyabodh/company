package com.sathyabodh.company.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Organization.class)
public class Organization_ {

	public volatile static SingularAttribute<Organization, String> name;
	public volatile static SingularAttribute<Organization, Company> company;
	
}
