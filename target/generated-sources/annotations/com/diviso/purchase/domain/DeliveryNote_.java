package com.diviso.purchase.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeliveryNote.class)
public abstract class DeliveryNote_ {

	public static volatile SingularAttribute<DeliveryNote, String> reference;
	public static volatile SingularAttribute<DeliveryNote, LocalDate> purchaseDate;
	public static volatile SetAttribute<DeliveryNote, Comment> comments;
	public static volatile SetAttribute<DeliveryNote, Rating> ratings;
	public static volatile SingularAttribute<DeliveryNote, Supplier> supplier;
	public static volatile SingularAttribute<DeliveryNote, String> orderReference;
	public static volatile SetAttribute<DeliveryNote, DeliveriesLine> deliveryLines;
	public static volatile SingularAttribute<DeliveryNote, Long> id;

}

