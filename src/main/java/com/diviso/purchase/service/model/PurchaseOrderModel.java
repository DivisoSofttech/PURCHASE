package com.diviso.purchase.service.model;


import java.time.LocalDate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import java.util.Objects;

/**
 * A DTO for the PurchaseOrder entity.
 */
public class PurchaseOrderModel implements Serializable {

    private Long id;

    private String reference;

    private LocalDate purchaseDate;

    private SupplierModel supplierModel;

    private StatussModel statussModel;
    
    private Set<PurchaseLineModel> purchaseLinesModel = new HashSet<>();
    
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

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PurchaseOrderModel purchaseOrderDTO = (PurchaseOrderModel) o;
        if(purchaseOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), purchaseOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PurchaseOrderDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            "}";
    }

	public SupplierModel getSupplierModel() {
		return supplierModel;
	}

	public void setSupplierModel(SupplierModel supplierModel) {
		this.supplierModel = supplierModel;
	}

	public StatussModel getStatussModel() {
		return statussModel;
	}

	public void setStatussModel(StatussModel statussModel) {
		this.statussModel = statussModel;
	}

	public Set<PurchaseLineModel> getPurchaseLinesModel() {
		return purchaseLinesModel;
	}

	public void setPurchaseLinesModel(Set<PurchaseLineModel> purchaseLinesModel) {
		this.purchaseLinesModel = purchaseLinesModel;
	}
}
