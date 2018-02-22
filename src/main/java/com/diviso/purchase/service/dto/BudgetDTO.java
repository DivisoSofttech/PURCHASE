package com.diviso.purchase.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Budget entity.
 */
public class BudgetDTO implements Serializable {

    private Long id;



    private String reference;


    private String name;

    private Double price;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BudgetDTO budgetDTO = (BudgetDTO) o;
        if(budgetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), budgetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BudgetDTO{" +
            "id=" + getId() +

            ", reference='" + getReference() + "'" +

            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
