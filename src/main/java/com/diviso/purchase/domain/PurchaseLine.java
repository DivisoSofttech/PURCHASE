package com.diviso.purchase.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PurchaseLine.
 */
@Entity
@Table(name = "purchase_line")
public class PurchaseLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_reference")
    private String productReference;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_tax")
    private Double productTax;

    @Column(name = "quantity")
    private Double quantity;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

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

    public PurchaseLine productReference(String productReference) {
        this.productReference = productReference;
        return this;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public PurchaseLine productPrice(Integer productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public void setProductPrice(Integer integer) {
        this.productPrice = integer;
    }

    public Double getProductTax() {
        return productTax;
    }

    public PurchaseLine productTax(Double productTax) {
        this.productTax = productTax;
        return this;
    }

    public void setProductTax(Double productTax) {
        this.productTax = productTax;
    }

    public Double getQuantity() {
        return quantity;
    }

    public PurchaseLine quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public PurchaseLine purchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        return this;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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
        PurchaseLine purchaseLine = (PurchaseLine) o;
        if (purchaseLine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), purchaseLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PurchaseLine{" +
            "id=" + getId() +
            ", productReference='" + getProductReference() + "'" +
            ", productPrice=" + getProductPrice() +
            ", productTax=" + getProductTax() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
