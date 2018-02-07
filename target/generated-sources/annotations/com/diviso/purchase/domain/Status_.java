package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Status.class)
public abstract class Status_ {

	public static volatile SetAttribute<Status, Quotation> quotations;
	public static volatile SingularAttribute<Status, String> name;
	public static volatile SingularAttribute<Status, Long> id;
	public static volatile SingularAttribute<Status, String> statusLevel;
	public static volatile SetAttribute<Status, PurchaseOrder> purchaseOrders;

}

