package com.diviso.purchase.service.model;

import java.util.Objects;



public class RatingTypeModel {
	

	   
	    private Long id;

	    
	    private String reference;

	    
	    private String type;

	   
	    private RatingModel ratingModel;

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

	    public RatingTypeModel reference(String reference) {
	        this.reference = reference;
	        return this;
	    }

	    public void setReference(String reference) {
	        this.reference = reference;
	    }

	    public String getType() {
	        return type;
	    }

	    public RatingTypeModel type(String type) {
	        this.type = type;
	        return this;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public RatingModel getRatingModel() {
	        return ratingModel;
	    }

	    public RatingTypeModel rating(RatingModel ratingModel) {
	        this.ratingModel = ratingModel;
	        return this;
	    }

	    public void setRatingModel(RatingModel ratingModel) {
	        this.ratingModel = ratingModel;
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
	        RatingTypeModel ratingTypeModel = (RatingTypeModel) o;
	        if (ratingTypeModel.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), ratingTypeModel.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(getId());
	    }

	    @Override
	    public String toString() {
	        return "RatingTypeModel{" +
	            "id=" + getId() +
	            ", reference='" + getReference() + "'" +
	            ", type='" + getType() + "'" +
	            "}";
	    }

}
