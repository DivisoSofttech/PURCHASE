package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Supplier.
 */
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private Integer reference;

    @OneToOne
    @JoinColumn(unique = true)
    private Address permanentAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private Contact contact;

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<Address> temporaryAddresses = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<Quotation> quotations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReference() {
        return reference;
    }

    public Supplier reference(Integer reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public Supplier permanentAddress(Address address) {
        this.permanentAddress = address;
        return this;
    }

    public void setPermanentAddress(Address address) {
        this.permanentAddress = address;
    }

    public Contact getContact() {
        return contact;
    }

    public Supplier contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<Address> getTemporaryAddresses() {
        return temporaryAddresses;
    }

    public Supplier temporaryAddresses(Set<Address> addresses) {
        this.temporaryAddresses = addresses;
        return this;
    }

    public Supplier addTemporaryAddress(Address address) {
        this.temporaryAddresses.add(address);
        address.setSupplier(this);
        return this;
    }

    public Supplier removeTemporaryAddress(Address address) {
        this.temporaryAddresses.remove(address);
        address.setSupplier(null);
        return this;
    }

    public void setTemporaryAddresses(Set<Address> addresses) {
        this.temporaryAddresses = addresses;
    }

    public Set<Quotation> getQuotations() {
        return quotations;
    }

    public Supplier quotations(Set<Quotation> quotations) {
        this.quotations = quotations;
        return this;
    }

    public Supplier addQuotation(Quotation quotation) {
        this.quotations.add(quotation);
        quotation.setSupplier(this);
        return this;
    }

    public Supplier removeQuotation(Quotation quotation) {
        this.quotations.remove(quotation);
        quotation.setSupplier(null);
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
        Supplier supplier = (Supplier) o;
        if (supplier.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Supplier{" +
            "id=" + getId() +
            ", reference=" + getReference() +
            "}";
    }
}
