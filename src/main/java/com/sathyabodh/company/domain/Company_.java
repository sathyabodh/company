package com.sathyabodh.company.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Company.class)
public class Company_ {

	public volatile static SingularAttribute<Company, String> name;
}
