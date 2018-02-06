package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Status.
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status_level")
    private String statusLevel;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private Set<Quotation> quotations = new HashSet<>();

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Status name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusLevel() {
        return statusLevel;
    }

    public Status statusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
        return this;
    }

    public void setStatusLevel(String statusLevel) {
        this.statusLevel = statusLevel;
    }

    public Set<Quotation> getQuotations() {
        return quotations;
    }

    public Status quotations(Set<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }

    public Status addQuotations(Quotation quotation) {
        this.quotations.add(quotation);
        quotation.setStatus(this);
        return this;
    }

    public Status removeQuotations(Quotation quotation) {
        this.quotations.remove(quotation);
        quotation.setStatus(null);
        return this;
    }

    public void setQuotations(Set<Quotation> quotations) {
        this.quotations = quotations;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public Status purchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
        return this;
    }

    public Status addPurchaseOrders(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.add(purchaseOrder);
        purchaseOrder.setStatus(this);
        return this;
    }

    public Status removePurchaseOrders(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.remove(purchaseOrder);
        purchaseOrder.setStatus(null);
        return this;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
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
        Status status = (Status) o;
        if (status.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), status.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Status{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", statusLevel='" + getStatusLevel() + "'" +
            "}";
    }
}
