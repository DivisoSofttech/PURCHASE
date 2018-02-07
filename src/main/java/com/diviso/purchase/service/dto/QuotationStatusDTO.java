package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the QuotationStatus entity.
 */
public class QuotationStatusDTO implements Serializable {

    private Long id;

    private Integer statusId;

    private String statusLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusLevel() {
        return statusLevel;
    }

    public void setStatusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuotationStatusDTO quotationStatusDTO = (QuotationStatusDTO) o;
        if(quotationStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationStatusDTO{" +
            "id=" + getId() +
            ", statusId=" + getStatusId() +
            ", statusLevel='" + getStatusLevel() + "'" +
            "}";
    }
}
