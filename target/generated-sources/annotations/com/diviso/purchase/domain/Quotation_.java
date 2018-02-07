package com.diviso.purchase.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Quotation.class)
public abstract class Quotation_ {

	public static volatile SingularAttribute<Quotation, String> reference;
	public static volatile SingularAttribute<Quotation, LocalDate> issuedDate;
	public static volatile SingularAttribute<Quotation, Supplier> supplier;
	public static volatile SetAttribute<Quotation, QuotationLine> quotationLines;
	public static volatile SingularAttribute<Quotation, Long> id;
	public static volatile SingularAttribute<Quotation, Statuss> statuss;

}

