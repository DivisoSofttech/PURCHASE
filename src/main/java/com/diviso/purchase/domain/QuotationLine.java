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

    @Column(name = "product_reference")
    private String productReference;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_tax")
    private Float productTax;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

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

    public String getProductReference() {
        return productReference;
    }

    public QuotationLine productReference(String productReference) {
        this.productReference = productReference;
        return this;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public QuotationLine productPrice(Integer productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Float getProductTax() {
        return productTax;
    }

    public QuotationLine productTax(Float productTax) {
        this.productTax = productTax;
        return this;
    }

    public void setProductTax(Float productTax) {
        this.productTax = productTax;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public QuotationLine availableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
        return this;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
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
            ", productReference='" + getProductReference() + "'" +
            ", productPrice=" + getProductPrice() +
            ", productTax=" + getProductTax() +
            ", availableQuantity=" + getAvailableQuantity() +
            ", isSelect='" + isIsSelect() + "'" +
            "}";
    }
}
