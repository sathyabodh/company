package com.sathyabodh.company.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

@StaticMetamodel(Employee.class)
public class Employee_ {

	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Department> department;
	public static volatile SingularAttribute<Employee, String> extensionNumber;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, DateTime> joinDateTime;
	public static volatile SingularAttribute<Employee, DateTime> endDateTime;
}
