package com.diviso.purchase.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Alert.
 */
@Entity
@Table(name = "alert")
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    private Budget budget;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPercentage() {
        return percentage;
    }

    public Alert percentage(String percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Double getPrice() {
        return price;
    }

    public Alert price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Budget getBudget() {
        return budget;
    }

    public Alert budget(Budget budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
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
        Alert alert = (Alert) o;
        if (alert.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alert.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Alert{" +
            "id=" + getId() +
            ", percentage='" + getPercentage() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
