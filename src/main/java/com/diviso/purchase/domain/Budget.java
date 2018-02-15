package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Budget.
 */
@Entity
@Table(name = "budget")
public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "budget")
    @JsonIgnore
    private Set<Alert> alerts = new HashSet<>();

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

    public Budget reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public Budget name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public Budget price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }

    public Budget alerts(Set<Alert> alerts) {
        this.alerts = alerts;
        return this;
    }

    public Budget addAlert(Alert alert) {
        this.alerts.add(alert);
        alert.setBudget(this);
        return this;
    }

    public Budget removeAlert(Alert alert) {
        this.alerts.remove(alert);
        alert.setBudget(null);
        return this;
    }

    public void setAlerts(Set<Alert> alerts) {
        this.alerts = alerts;
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
        Budget budget = (Budget) o;
        if (budget.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), budget.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Budget{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
