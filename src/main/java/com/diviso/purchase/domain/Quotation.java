package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Quotation.
 */
@Entity
@Table(name = "quotation")
public class Quotation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "quotation")
    @JsonIgnore
    private Set<QuotationLine> quotationLines = new HashSet<>();

    @ManyToOne
    private Status status;

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

    public Quotation reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public Quotation issuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
        return this;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Quotation supplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<QuotationLine> getQuotationLines() {
        return quotationLines;
    }

    public Quotation quotationLines(Set<QuotationLine> quotationLines) {
        this.quotationLines = quotationLines;
        return this;
    }

    public Quotation addQuotationLines(QuotationLine quotationLine) {
        this.quotationLines.add(quotationLine);
        quotationLine.setQuotation(this);
        return this;
    }

    public Quotation removeQuotationLines(QuotationLine quotationLine) {
        this.quotationLines.remove(quotationLine);
        quotationLine.setQuotation(null);
        return this;
    }

    public void setQuotationLines(Set<QuotationLine> quotationLines) {
        this.quotationLines = quotationLines;
    }

    public Status getStatus() {
        return status;
    }

    public Quotation status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        Quotation quotation = (Quotation) o;
        if (quotation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quotation{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", issuedDate='" + getIssuedDate() + "'" +
            "}";
    }
}
