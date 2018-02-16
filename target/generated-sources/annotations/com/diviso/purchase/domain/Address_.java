package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, Long> pinCode;
	public static volatile SingularAttribute<Address, Supplier> supplier;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> place;
	public static volatile SingularAttribute<Address, String> state;

}

