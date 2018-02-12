package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RatingType entity.
 */
public class RatingTypeDTO implements Serializable {

    private Long id;

    private String reference;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RatingTypeDTO ratingTypeDTO = (RatingTypeDTO) o;
        if(ratingTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ratingTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RatingTypeDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
