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

    @Column(name = "quotation_id")
    private Long quotationId;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private Set<PurchaseLine> purchaseLines = new HashSet<>();

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private Set<Quotation> quotations = new HashSet<>();

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

    public Long getQuotationId() {
        return quotationId;
    }

    public PurchaseOrder quotationId(Long quotationId) {
        this.quotationId = quotationId;
        return this;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
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

    public Set<PurchaseLine> getPurchaseLines() {
        return purchaseLines;
    }

    public PurchaseOrder purchaseLines(Set<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
        return this;
    }

    public PurchaseOrder addPurchaseLine(PurchaseLine purchaseLine) {
        this.purchaseLines.add(purchaseLine);
        purchaseLine.setPurchaseOrder(this);
        return this;
    }

    public PurchaseOrder removePurchaseLine(PurchaseLine purchaseLine) {
        this.purchaseLines.remove(purchaseLine);
        purchaseLine.setPurchaseOrder(null);
        return this;
    }

    public void setPurchaseLines(Set<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
    }

    public Set<Quotation> getQuotations() {
        return quotations;
    }

    public PurchaseOrder quotations(Set<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }

    public PurchaseOrder addQuotation(Quotation quotation) {
        this.quotations.add(quotation);
        quotation.setPurchaseOrder(this);
        return this;
    }

    public PurchaseOrder removeQuotation(Quotation quotation) {
        this.quotations.remove(quotation);
        quotation.setPurchaseOrder(null);
        return this;
    }

    public void setQuotations(Set<Quotation> quotations) {
        this.quotations = quotations;
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
            ", quotationId=" + getQuotationId() +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            "}";
    }
}
