package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Alert entity.
 */
public class AlertDTO implements Serializable {

    private Long id;

    private String percentage;

    private Double price;

    private Long budgetId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlertDTO alertDTO = (AlertDTO) o;
        if(alertDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alertDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlertDTO{" +
            "id=" + getId() +
            ", percentage='" + getPercentage() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
