package com.diviso.purchase.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DeliveryNote entity.
 */
public class DeliveryNoteDTO implements Serializable {

    private Long id;

    private String reference;

    private String orderReference;

    private LocalDate purchaseDate;

    private Set<RatingDTO> ratings = new HashSet<>();

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

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Set<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(Set<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeliveryNoteDTO deliveryNoteDTO = (DeliveryNoteDTO) o;
        if(deliveryNoteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryNoteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryNoteDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", orderReference='" + getOrderReference() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            "}";
    }
}
