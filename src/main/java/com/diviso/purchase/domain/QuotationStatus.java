package com.diviso.purchase.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A QuotationStatus.
 */
@Entity
@Table(name = "quotation_status")
public class QuotationStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_level")
    private String statusLevel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public QuotationStatus statusId(Integer statusId) {
        this.statusId = statusId;
        return this;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusLevel() {
        return statusLevel;
    }

    public QuotationStatus statusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
        return this;
    }

    public void setStatusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
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
        QuotationStatus quotationStatus = (QuotationStatus) o;
        if (quotationStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationStatus{" +
            "id=" + getId() +
            ", statusId=" + getStatusId() +
            ", statusLevel='" + getStatusLevel() + "'" +
            "}";
    }
}
