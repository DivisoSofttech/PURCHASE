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
    private String reference;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(unique = true)
    private Address permanentAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private Contact contact;

    @OneToOne
    @JoinColumn(unique = true)
    private Rating rating;

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<Address> temporaryAddresses = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private Set<Quotation> quatations = new HashSet<>();

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

    public Supplier reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFirstName() {
        return firstName;
    }

    public Supplier firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Supplier lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Rating getRating() {
        return rating;
    }

    public Supplier rating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Address> getTemporaryAddresses() {
        return temporaryAddresses;
    }

    public Supplier temporaryAddresses(Set<Address> addresses) {
        this.temporaryAddresses = addresses;
        return this;
    }

    public Supplier addTemporaryAddresses(Address address) {
        this.temporaryAddresses.add(address);
        address.setSupplier(this);
        return this;
    }

    public Supplier removeTemporaryAddresses(Address address) {
        this.temporaryAddresses.remove(address);
        address.setSupplier(null);
        return this;
    }

    public void setTemporaryAddresses(Set<Address> addresses) {
        this.temporaryAddresses = addresses;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public Supplier purchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
        return this;
    }

    public Supplier addPurchaseOrders(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.add(purchaseOrder);
        purchaseOrder.setSupplier(this);
        return this;
    }

    public Supplier removePurchaseOrders(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.remove(purchaseOrder);
        purchaseOrder.setSupplier(null);
        return this;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public Set<Quotation> getQuatations() {
        return quatations;
    }

    public Supplier quatations(Set<Quotation> quotations) {
        this.quatations = quotations;
        return this;
    }

    public Supplier addQuatations(Quotation quotation) {
        this.quatations.add(quotation);
        quotation.setSupplier(this);
        return this;
    }

    public Supplier removeQuatations(Quotation quotation) {
        this.quatations.remove(quotation);
        quotation.setSupplier(null);
        return this;
    }

    public void setQuatations(Set<Quotation> quotations) {
        this.quatations = quotations;
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
            ", reference='" + getReference() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            "}";
    }
}
