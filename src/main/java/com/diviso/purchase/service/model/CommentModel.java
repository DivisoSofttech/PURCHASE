package com.diviso.purchase.service.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.diviso.purchase.domain.Comment;
import com.diviso.purchase.domain.DeliveryNote;

public class CommentModel {
	 

	   
	    private Long id;

	    
	    private String reference;

	   
	    private String comments;

	    
	    private DeliveryNoteModel deliveryNoteModel;

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

	    public CommentModel reference(String reference) {
	        this.reference = reference;
	        return this;
	    }

	    public void setReference(String reference) {
	        this.reference = reference;
	    }

	    public String getComments() {
	        return comments;
	    }

	    public CommentModel comments(String comments) {
	        this.comments = comments;
	        return this;
	    }

	    public void setComments(String comments) {
	        this.comments = comments;
	    }

	    public DeliveryNoteModel getDeliveryNoteModel() {
	        return deliveryNoteModel;
	    }

	    public CommentModel deliveryNote(DeliveryNoteModel deliveryNoteModel) {
	        this.deliveryNoteModel = deliveryNoteModel;
	        return this;
	    }

	    public void setDeliveryNoteModel(DeliveryNoteModel deliveryNoteModel) {
	        this.deliveryNoteModel = deliveryNoteModel;
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
	        Comment comment = (Comment) o;
	        if (comment.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), comment.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(getId());
	    }

	    @Override
	    public String toString() {
	        return "CommentModel{" +
	            "id=" + getId() +
	            ", reference='" + getReference() + "'" +
	            ", comments='" + getComments() + "'" +
	            "}";
	    }

}
