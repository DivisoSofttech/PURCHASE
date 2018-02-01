package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DeliveryNote.
 */
@Entity
@Table(name = "delivery_note")
public class DeliveryNote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "order_reference")
    private String orderReference;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "quotation_id")
    private Long quotationId;

    @OneToOne
    @JoinColumn(unique = true)
    private Rating rating;

    @OneToMany(mappedBy = "deliveryNote")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

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

    public DeliveryNote reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public DeliveryNote orderReference(String orderReference) {
        this.orderReference = orderReference;
        return this;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public DeliveryNote supplierId(Long supplierId) {
        this.supplierId = supplierId;
        return this;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public DeliveryNote purchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getQuotationId() {
        return quotationId;
    }

    public DeliveryNote quotationId(Long quotationId) {
        this.quotationId = quotationId;
        return this;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }

    public Rating getRating() {
        return rating;
    }

    public DeliveryNote rating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public DeliveryNote comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public DeliveryNote addComment(Comment comment) {
        this.comments.add(comment);
        comment.setDeliveryNote(this);
        return this;
    }

    public DeliveryNote removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setDeliveryNote(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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
        DeliveryNote deliveryNote = (DeliveryNote) o;
        if (deliveryNote.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryNote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryNote{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", orderReference='" + getOrderReference() + "'" +
            ", supplierId=" + getSupplierId() +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            ", quotationId=" + getQuotationId() +
            "}";
    }
}
