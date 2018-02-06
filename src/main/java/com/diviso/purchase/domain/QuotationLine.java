package com.diviso.purchase.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationLine.
 */
@Entity
@Table(name = "quotation_line")
public class QuotationLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "price")
    private Integer price;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "available_quantity")
    private Double availableQuantity;

    @Column(name = "is_select")
    private Boolean isSelect;

    @ManyToOne
    private Quotation quotation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public QuotationLine reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getPrice() {
        return price;
    }

    public QuotationLine price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public QuotationLine tax(Double tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAvailableQuantity() {
        return availableQuantity;
    }

    public QuotationLine availableQuantity(Double availableQuantity) {
        this.availableQuantity = availableQuantity;
        return this;
    }

    public void setAvailableQuantity(Double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Boolean isIsSelect() {
        return isSelect;
    }

    public QuotationLine isSelect(Boolean isSelect) {
        this.isSelect = isSelect;
        return this;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public QuotationLine quotation(Quotation quotation) {
        this.quotation = quotation;
        return this;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuotationLine quotationLine = (QuotationLine) o;
        if (quotationLine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationLine{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", price=" + getPrice() +
            ", tax=" + getTax() +
            ", availableQuantity=" + getAvailableQuantity() +
            ", isSelect='" + isIsSelect() + "'" +
            "}";
    }
}
