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

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "deliveryNote")
    @JsonIgnore
    private Set<DeliveryLine> deliveryLines = new HashSet<>();

    @OneToMany(mappedBy = "deliveryNote")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "delivery_note_ratings",
               joinColumns = @JoinColumn(name="delivery_notes_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="ratings_id", referencedColumnName="id"))
    private Set<Rating> ratings = new HashSet<>();

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

    public Set<DeliveryLine> getDeliveryLines() {
        return deliveryLines;
    }

    public DeliveryNote deliveryLines(Set<DeliveryLine> deliveryLines) {
        this.deliveryLines = deliveryLines;
        return this;
    }

    public DeliveryNote addDeliveryLines(DeliveryLine deliveryLine) {
        this.deliveryLines.add(deliveryLine);
        deliveryLine.setDeliveryNote(this);
        return this;
    }

    public DeliveryNote removeDeliveryLines(DeliveryLine deliveryLine) {
        this.deliveryLines.remove(deliveryLine);
        deliveryLine.setDeliveryNote(null);
        return this;
    }

    public void setDeliveryLines(Set<DeliveryLine> deliveryLines) {
        this.deliveryLines = deliveryLines;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public DeliveryNote comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public DeliveryNote addComments(Comment comment) {
        this.comments.add(comment);
        comment.setDeliveryNote(this);
        return this;
    }

    public DeliveryNote removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setDeliveryNote(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public DeliveryNote ratings(Set<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public DeliveryNote addRatings(Rating rating) {
        this.ratings.add(rating);
        return this;
    }

    public DeliveryNote removeRatings(Rating rating) {
        this.ratings.remove(rating);
        return this;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
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
            ", purchaseDate='" + getPurchaseDate() + "'" +
            "}";
    }
}
