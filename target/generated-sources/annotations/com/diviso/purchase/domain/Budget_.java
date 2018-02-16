package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Budget.class)
public abstract class Budget_ {

	public static volatile SingularAttribute<Budget, String> reference;
	public static volatile SetAttribute<Budget, Alert> alerts;
	public static volatile SingularAttribute<Budget, Double> price;
	public static volatile SingularAttribute<Budget, String> name;
	public static volatile SingularAttribute<Budget, Long> id;

}

