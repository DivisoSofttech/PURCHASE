package com.diviso.purchase.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DeliveriesLine.
 */
@Entity
@Table(name = "deliveries_line")
public class DeliveriesLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "price")
    private Double price;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "quantity")
    private Double quantity;

    @ManyToOne
    private DeliveryNote deliveryNote;

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

    public DeliveriesLine reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrice() {
        return price;
    }

    public DeliveriesLine price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public DeliveriesLine tax(Double tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getQuantity() {
        return quantity;
    }

    public DeliveriesLine quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public DeliveriesLine deliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
        return this;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
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
        DeliveriesLine deliveriesLine = (DeliveriesLine) o;
        if (deliveriesLine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveriesLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveriesLine{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", price=" + getPrice() +
            ", tax=" + getTax() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
