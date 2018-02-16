package com.diviso.purchase.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseOrder.class)
public abstract class PurchaseOrder_ {

	public static volatile SingularAttribute<PurchaseOrder, String> reference;
	public static volatile SingularAttribute<PurchaseOrder, LocalDate> purchaseDate;
	public static volatile SingularAttribute<PurchaseOrder, Supplier> supplier;
	public static volatile SetAttribute<PurchaseOrder, PurchaseLine> purchaseLines;
	public static volatile SingularAttribute<PurchaseOrder, Long> id;
	public static volatile SingularAttribute<PurchaseOrder, Statuss> statuss;

}

