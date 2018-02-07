package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the QuotationLine entity.
 */
public class QuotationLineDTO implements Serializable {

    private Long id;

    private String reference;

    private Integer price;

    private Double tax;

    private Double availableQuantity;

    private Boolean isSelect;

    private Long quotationId;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Boolean isIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public Long getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuotationLineDTO quotationLineDTO = (QuotationLineDTO) o;
        if(quotationLineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationLineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationLineDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", price=" + getPrice() +
            ", tax=" + getTax() +
            ", availableQuantity=" + getAvailableQuantity() +
            ", isSelect='" + isIsSelect() + "'" +
            "}";
    }
}
