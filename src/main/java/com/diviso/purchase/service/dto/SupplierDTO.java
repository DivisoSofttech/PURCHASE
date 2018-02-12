package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Supplier entity.
 */
public class SupplierDTO implements Serializable {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private Long permanentAddressId;

    private Long contactId;

    private Long ratingId;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPermanentAddressId() {
        return permanentAddressId;
    }

    public void setPermanentAddressId(Long addressId) {
        this.permanentAddressId = addressId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierDTO supplierDTO = (SupplierDTO) o;
        if(supplierDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplierDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            "}";
    }
}
