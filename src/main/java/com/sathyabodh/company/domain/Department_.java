package com.sathyabodh.company.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Department.class)
public class Department_ {

	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, Organization> organization;
}
