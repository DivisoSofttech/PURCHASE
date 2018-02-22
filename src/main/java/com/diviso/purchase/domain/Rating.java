package com.diviso.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Rating.
 */
@Entity
@Table(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "ratings")
    private Integer ratings;

    @OneToMany(mappedBy = "rating")
    @JsonIgnore
    private Set<RatingType> ratingTypes = new HashSet<>();

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

    public Rating reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getRatings() {
        return ratings;
    }

    public Rating ratings(Integer ratings) {
        this.ratings = ratings;
        return this;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Set<RatingType> getRatingTypes() {
        return ratingTypes;
    }

    public Rating ratingTypes(Set<RatingType> ratingTypes) {
        this.ratingTypes = ratingTypes;
        return this;
    }

    public Rating addRatingType(RatingType ratingType) {
        this.ratingTypes.add(ratingType);
        ratingType.setRating(this);
        return this;
    }

    public Rating removeRatingType(RatingType ratingType) {
        this.ratingTypes.remove(ratingType);
        ratingType.setRating(null);
        return this;
    }

    public void setRatingTypes(Set<RatingType> ratingTypes) {
        this.ratingTypes = ratingTypes;
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
        Rating rating = (Rating) o;
        if (rating.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rating.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Rating{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", ratings=" + getRatings() +
            "}";
    }
}
