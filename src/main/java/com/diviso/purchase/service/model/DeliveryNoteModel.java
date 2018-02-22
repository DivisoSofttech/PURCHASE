package com.diviso.purchase.service.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



public class DeliveryNoteModel {
	

	   
	    private Long id;

	    
	    private String reference;

	   
	    private String orderReference;

	    
	    private LocalDate deliveredDate;

	    
	    private SupplierModel supplierModel;

	   
	    private Set<DeliveriesLineModel> deliveryLinesModel = new HashSet<>();

	   
	    private Set<CommentModel> commentsModel = new HashSet<>();

	   
	    private Set<RatingModel> ratingsModel = new HashSet<>();

	    
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

	    public DeliveryNoteModel reference(String reference) {
	        this.reference = reference;
	        return this;
	    }

	    public void setReference(String reference) {
	        this.reference = reference;
	    }

	    public String getOrderReference() {
	        return orderReference;
	    }

	    public DeliveryNoteModel orderReference(String orderReference) {
	        this.orderReference = orderReference;
	        return this;
	    }

	    public void setOrderReference(String orderReference) {
	        this.orderReference = orderReference;
	    }

	    public LocalDate getDeliveredDate() {
	        return deliveredDate;
	    }

	    public DeliveryNoteModel deliveredDate(LocalDate deliveredDate) {
	        this.deliveredDate = deliveredDate;
	        return this;
	    }

	    public void setDeliveredDate(LocalDate deliveredDate) {
	        this.deliveredDate = deliveredDate;
	    }

	    public SupplierModel getSupplierModel() {
	        return supplierModel;
	    }

	    public DeliveryNoteModel supplier(SupplierModel supplierModel) {
	        this.supplierModel = supplierModel;
	        return this;
	    }

	    public void setSupplierModel(SupplierModel supplierModel) {
	        this.supplierModel = supplierModel;
	    }

	    public Set<DeliveriesLineModel> getDeliveryLinesModel() {
	        return deliveryLinesModel;
	    }

	    public DeliveryNoteModel deliveryLinesModel(Set<DeliveriesLineModel> deliveriesLinesModel) {
	        this.deliveryLinesModel = deliveriesLinesModel;
	        return this;
	    }

	    public DeliveryNoteModel addDeliveryLinesModel(DeliveriesLineModel deliveriesLineModel) {
	        this.deliveryLinesModel.add(deliveriesLineModel);
	        deliveriesLineModel.setDeliveryNoteModel(this);
	        return this;
	    }

	    public DeliveryNoteModel removeDeliveryLines(DeliveriesLineModel deliveriesLineModel) {
	        this.deliveryLinesModel.remove(deliveriesLineModel);
	        deliveriesLineModel.setDeliveryNoteModel(null);
	        return this;
	    }

	    public void setDeliveryLines(Set<DeliveriesLineModel> deliveriesLines) {
	        this.deliveryLinesModel = deliveriesLines;
	    }

	    public Set<CommentModel> getCommentsModel() {
	        return commentsModel;
	    }

	    public DeliveryNoteModel comments(Set<CommentModel> commentsModel) {
	        this.commentsModel = commentsModel;
	        return this;
	    }

	    public DeliveryNoteModel addComments(CommentModel commentModel) {
	        this.commentsModel.add(commentModel);
	        commentModel.setDeliveryNoteModel(this);
	        return this;
	    }

	    public DeliveryNoteModel removeComments(CommentModel commentModel) {
	        this.commentsModel.remove(commentModel);
	        commentModel.setDeliveryNoteModel(null);
	        return this;
	    }

	    public void setComments(Set<CommentModel> commentsModel) {
	        this.commentsModel = commentsModel;
	    }

	    public Set<RatingModel> getRatings() {
	        return ratingsModel;
	    }

	    public DeliveryNoteModel ratings(Set<RatingModel> ratingsModel) {
	        this.ratingsModel = ratingsModel;
	        return this;
	    }

	    public DeliveryNoteModel addRatings(RatingModel ratingModel) {
	        this.ratingsModel.add(ratingModel);
	        return this;
	    }

	    public DeliveryNoteModel removeRatings(RatingModel ratingModel) {
	        this.ratingsModel.remove(ratingModel);
	        return this;
	    }

	    public void setRatings(Set<RatingModel> ratingsModel) {
	        this.ratingsModel = ratingsModel;
	    }

	    public Set<RatingTypeModel> getRatingTypes() {
	        return ratingTypesModel;
	    }

	    public DeliveryNoteModel ratingTypes(Set<RatingTypeModel> ratingTypesModel) {
	        this.ratingTypesModel = ratingTypesModel;
	        return this;
	    }

	    public DeliveryNoteModel addRatingType(RatingTypeModel ratingTypeModel) {
	        this.ratingTypesModel.add(ratingTypeModel);
	        return this;
	    }

	    public DeliveryNoteModel removeRatingType(RatingTypeModel ratingTypeModel) {
	        this.ratingTypesModel.remove(ratingTypeModel);
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
	        DeliveryNoteModel deliveryNoteModel = (DeliveryNoteModel) o;
	        if (deliveryNoteModel.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), deliveryNoteModel.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(getId());
	    }

	    @Override
	    public String toString() {
	        return "DeliveryNoteModel{" +
	            "id=" + getId() +
	            ", reference='" + getReference() + "'" +
	            ", orderReference='" + getOrderReference() + "'" +
	            ", deliveredDate='" + getDeliveredDate() + "'" +
	            "}";
	    }
}
