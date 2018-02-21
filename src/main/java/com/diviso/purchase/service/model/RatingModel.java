package com.diviso.purchase.service.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



public class RatingModel {
	

	  
	    private Long id;

	    
	    private String reference;

	   
	    private Integer ratings;

	    
	    private Set<RatingTypeModel> ratingTypesModel = new HashSet<>();

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

	    public RatingModel reference(String reference) {
	        this.reference = reference;
	        return this;
	    }

	    public void setReference(String reference) {
	        this.reference = reference;
	    }

	    public Integer getRatings() {
	        return ratings;
	    }

	    public RatingModel ratings(Integer ratings) {
	        this.ratings = ratings;
	        return this;
	    }

	    public void setRatings(Integer ratings) {
	        this.ratings = ratings;
	    }

	    public Set<RatingTypeModel> getRatingTypes() {
	        return ratingTypesModel;
	    }

	    public RatingModel ratingTypes(Set<RatingTypeModel> ratingTypesModel) {
	        this.ratingTypesModel = ratingTypesModel;
	        return this;
	    }

	    public RatingModel addRatingType(RatingTypeModel ratingTypeModel) {
	        this.ratingTypesModel.add(ratingTypeModel);
	        ratingTypeModel.setRatingModel(this);
	        return this;
	    }

	    public RatingModel removeRatingType(RatingTypeModel ratingTypeModel) {
	        this.ratingTypesModel.remove(ratingTypeModel);
	        ratingTypeModel.setRatingModel(null);
	        return this;
	    }

	    public void setRatingTypes(Set<RatingTypeModel> ratingTypesModel) {
	        this.ratingTypesModel = ratingTypesModel;
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
	        RatingModel ratingModel = (RatingModel) o;
	        if (ratingModel.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), ratingModel.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(getId());
	    }

	    @Override
	    public String toString() {
	        return "RatingModel{" +
	            "id=" + getId() +
	            ", reference='" + getReference() + "'" +
	            ", ratings=" + getRatings() +
	            "}";
	    }

}
