package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "reference")
    private String reference;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Status status;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private Set<PurchaseLine> purchaseLines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public PurchaseOrder supplierId(Long supplierId) {
        this.supplierId = supplierId;
        return this;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getReference() {
        return reference;
    }

    public PurchaseOrder reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public PurchaseOrder purchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public PurchaseOrder supplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Status getStatus() {
        return status;
    }

    public PurchaseOrder status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<PurchaseLine> getPurchaseLines() {
        return purchaseLines;
    }

    public PurchaseOrder purchaseLines(Set<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
        return this;
    }

    public PurchaseOrder addPurchaseLines(PurchaseLine purchaseLine) {
        this.purchaseLines.add(purchaseLine);
        purchaseLine.setPurchaseOrder(this);
        return this;
    }

    public PurchaseOrder removePurchaseLines(PurchaseLine purchaseLine) {
        this.purchaseLines.remove(purchaseLine);
        purchaseLine.setPurchaseOrder(null);
        return this;
    }

    public void setPurchaseLines(Set<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
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
        PurchaseOrder purchaseOrder = (PurchaseOrder) o;
        if (purchaseOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), purchaseOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
            "id=" + getId() +
            ", supplierId=" + getSupplierId() +
            ", reference='" + getReference() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            "}";
    }
}
