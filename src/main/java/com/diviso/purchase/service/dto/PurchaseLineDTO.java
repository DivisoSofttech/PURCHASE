package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PurchaseLine entity.
 */
public class PurchaseLineDTO implements Serializable {

    private Long id;

    private String productReference;

    private Double productPrice;

    private Double productTax;

    private Double quantity;

    private Long purchaseOrderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductTax() {
        return productTax;
    }

    public void setProductTax(Double productTax) {
        this.productTax = productTax;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PurchaseLineDTO purchaseLineDTO = (PurchaseLineDTO) o;
        if(purchaseLineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), purchaseLineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PurchaseLineDTO{" +
            "id=" + getId() +
            ", productReference='" + getProductReference() + "'" +
            ", productPrice=" + getProductPrice() +
            ", productTax=" + getProductTax() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
