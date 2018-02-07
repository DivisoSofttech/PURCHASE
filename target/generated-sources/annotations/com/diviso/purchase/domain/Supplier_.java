package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supplier.class)
public abstract class Supplier_ {

	public static volatile SingularAttribute<Supplier, String> reference;
	public static volatile SingularAttribute<Supplier, String> firstName;
	public static volatile SingularAttribute<Supplier, String> lastName;
	public static volatile SetAttribute<Supplier, Quotation> quatations;
	public static volatile SingularAttribute<Supplier, Contact> contact;
	public static volatile SingularAttribute<Supplier, Long> id;
	public static volatile SingularAttribute<Supplier, Address> permanentAddress;
	public static volatile SetAttribute<Supplier, Address> temporaryAddresses;
	public static volatile SetAttribute<Supplier, PurchaseOrder> purchaseOrders;

}

