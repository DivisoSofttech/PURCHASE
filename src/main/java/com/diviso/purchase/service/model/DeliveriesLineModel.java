package com.diviso.purchase.service.model;

import java.util.Objects;



public class DeliveriesLineModel {
	private static final long serialVersionUID = 1L;

   
    private Long id;

   
    private String reference;

    
    private Double price;

   
    private Double tax;

    
    private Double quantity;

    
    private DeliveryNoteModel deliveryNoteModel;

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

    public DeliveriesLineModel reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrice() {
        return price;
    }

    public DeliveriesLineModel price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public DeliveriesLineModel tax(Double tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getQuantity() {
        return quantity;
    }

    public DeliveriesLineModel quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public DeliveryNoteModel getDeliveryNoteModel() {
        return deliveryNoteModel;
    }

    public DeliveriesLineModel deliveryNote(DeliveryNoteModel deliveryNoteModel) {
        this.deliveryNoteModel = deliveryNoteModel;
        return this;
    }

    public void setDeliveryNoteModel(DeliveryNoteModel deliveryNoteModel) {
        this.deliveryNoteModel = deliveryNoteModel;
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
        DeliveriesLineModel deliveriesLineModel = (DeliveriesLineModel) o;
        if (deliveriesLineModel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveriesLineModel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveriesLineModel{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", price=" + getPrice() +
            ", tax=" + getTax() +
            ", quantity=" + getQuantity() +
            "}";
    }

}
