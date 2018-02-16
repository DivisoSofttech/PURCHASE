package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Statuss.class)
public abstract class Statuss_ {

	public static volatile SetAttribute<Statuss, Quotation> quotations;
	public static volatile SingularAttribute<Statuss, String> name;
	public static volatile SingularAttribute<Statuss, Long> id;
	public static volatile SingularAttribute<Statuss, String> statusLevel;
	public static volatile SetAttribute<Statuss, PurchaseOrder> purchaseOrders;

}

