package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeliveryLine.class)
public abstract class DeliveryLine_ {

	public static volatile SingularAttribute<DeliveryLine, String> reference;
	public static volatile SingularAttribute<DeliveryLine, Double> quantity;
	public static volatile SingularAttribute<DeliveryLine, DeliveryNote> deliveryNote;
	public static volatile SingularAttribute<DeliveryLine, Double> price;
	public static volatile SingularAttribute<DeliveryLine, Double> tax;
	public static volatile SingularAttribute<DeliveryLine, Long> id;

}

