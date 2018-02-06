package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DeliveryLine entity.
 */
public class DeliveryLineDTO implements Serializable {

    private Long id;

    private String reference;

    private Double price;

    private Double tax;

    private Double quantity;

    private Long deliveryNoteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(Long deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeliveryLineDTO deliveryLineDTO = (DeliveryLineDTO) o;
        if(deliveryLineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryLineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryLineDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", price=" + getPrice() +
            ", tax=" + getTax() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
