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

    private LocalDate deliveredDate;

    private Long supplierId;

    private Set<RatingDTO> ratings = new HashSet<>();

    private Set<RatingTypeDTO> ratingTypes = new HashSet<>();

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

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Set<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(Set<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    public Set<RatingTypeDTO> getRatingTypes() {
        return ratingTypes;
    }

    public void setRatingTypes(Set<RatingTypeDTO> ratingTypes) {
        this.ratingTypes = ratingTypes;
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
            ", deliveredDate='" + getDeliveredDate() + "'" +
            "}";
    }
}
