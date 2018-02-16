package com.diviso.purchase.service.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Supplier entity.
 */
public class SupplierModel implements Serializable {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private AddressModel permanentAddressModel;

    private ContactModel contactModel;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierModel supplierDTO = (SupplierModel) o;
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

	public AddressModel getPermanentAddressModel() {
		return permanentAddressModel;
	}

	public void setPermanentAddressModel(AddressModel permanentAddressModel) {
		this.permanentAddressModel = permanentAddressModel;
	}

	public ContactModel getContactModel() {
		return contactModel;
	}

	public void setContactModel(ContactModel contactModel) {
		this.contactModel = contactModel;
	}
}
