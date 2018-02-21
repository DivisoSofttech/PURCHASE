package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import com.diviso.purchase.service.model.DeliveryNoteModel;

import java.time.LocalDate;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Service Interface for managing DeliveryNote.
 */
public interface DeliveryNoteService {

    /**
     * Save a deliveryNote.
     *
     * @param deliveryNoteDTO the entity to save
     * @return the persisted entity
     */
    DeliveryNoteDTO save(DeliveryNoteDTO deliveryNoteDTO);

    /**
     * Get all the deliveryNotes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DeliveryNoteDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryNote.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DeliveryNoteDTO findOne(Long id);

    /**
     * Delete the "id" deliveryNote.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    /**
     * Get all the deliveryNotes by order reference.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<DeliveryNoteDTO> findAllByOrderReference(String orderReference, Pageable pageable);

	/**
     * Get all the deliveryNotes by supplier id.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<DeliveryNoteDTO> findAllBySupplier_Id(Long supplierId, Pageable pageable);

	/**
     * Get all the deliveryNotes by delivered date between.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<DeliveryNoteDTO> findAllByDeliveredDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	/**
     * Get all the deliveryNotes by delivered date.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<DeliveryNoteDTO> findAllByDeliveredDate(LocalDate deliveredDate, Pageable pageable);

	/**
     * Get all the deliveryNotes by reference.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<DeliveryNoteDTO> findAllByReference(String reference, Pageable pageable);
	/**
     *This is a method which is used to send individual mail to the supplier 
     *
     * @param to,subject,text
     * @return the list of entities
     */
	public String sendMail(String to,String subject,String text);
	/**
     *This is a method which is used to send individual mail to the supplier with attachment 
     *
     * @param to,subject,text
     * @return the list of entities
     */
 	public String sendMailWithAttachments(String to,String subject,String text) throws MessagingException;

 	/**
     *This is a method which is used to update the inventory after delivery
     *
     * 
     * @return the String
     */
	String updateInventory();

	String sendMessageAsSms();

	DeliveryNoteModel marshelledFindOne(Long id);

}
