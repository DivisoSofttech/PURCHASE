package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeliveriesLine.class)
public abstract class DeliveriesLine_ {

	public static volatile SingularAttribute<DeliveriesLine, String> reference;
	public static volatile SingularAttribute<DeliveriesLine, Double> quantity;
	public static volatile SingularAttribute<DeliveriesLine, DeliveryNote> deliveryNote;
	public static volatile SingularAttribute<DeliveriesLine, Double> price;
	public static volatile SingularAttribute<DeliveriesLine, Double> tax;
	public static volatile SingularAttribute<DeliveriesLine, Long> id;

}

