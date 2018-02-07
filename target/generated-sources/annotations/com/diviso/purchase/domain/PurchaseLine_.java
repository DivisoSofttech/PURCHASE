package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseLine.class)
public abstract class PurchaseLine_ {

	public static volatile SingularAttribute<PurchaseLine, Double> productTax;
	public static volatile SingularAttribute<PurchaseLine, Double> quantity;
	public static volatile SingularAttribute<PurchaseLine, PurchaseOrder> purchaseOrder;
	public static volatile SingularAttribute<PurchaseLine, String> productReference;
	public static volatile SingularAttribute<PurchaseLine, Long> id;
	public static volatile SingularAttribute<PurchaseLine, Double> productPrice;

}

