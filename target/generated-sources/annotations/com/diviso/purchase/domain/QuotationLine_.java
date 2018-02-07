package com.diviso.purchase.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuotationLine.class)
public abstract class QuotationLine_ {

	public static volatile SingularAttribute<QuotationLine, String> reference;
	public static volatile SingularAttribute<QuotationLine, Double> availableQuantity;
	public static volatile SingularAttribute<QuotationLine, Integer> price;
	public static volatile SingularAttribute<QuotationLine, Double> tax;
	public static volatile SingularAttribute<QuotationLine, Boolean> isSelect;
	public static volatile SingularAttribute<QuotationLine, Long> id;
	public static volatile SingularAttribute<QuotationLine, Quotation> quotation;

}

