package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, String> reference;
	public static volatile SingularAttribute<Comment, String> comments;
	public static volatile SingularAttribute<Comment, DeliveryNote> deliveryNote;
	public static volatile SingularAttribute<Comment, Long> id;

}

