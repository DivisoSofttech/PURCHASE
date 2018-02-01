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

    private String productReference;

    private Integer productPrice;

    private Float productTax;

    private Integer availableQuantity;

    private Boolean isSelect;

    private Long quotationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Float getProductTax() {
        return productTax;
    }

    public void setProductTax(Float productTax) {
        this.productTax = productTax;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
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
            ", productReference='" + getProductReference() + "'" +
            ", productPrice=" + getProductPrice() +
            ", productTax=" + getProductTax() +
            ", availableQuantity=" + getAvailableQuantity() +
            ", isSelect='" + isIsSelect() + "'" +
            "}";
    }
}
