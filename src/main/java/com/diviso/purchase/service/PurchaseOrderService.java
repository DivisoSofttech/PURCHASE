package com.diviso.purchase.service;

import com.diviso.purchase.domain.Quotation;
import com.diviso.purchase.service.dto.PurchaseOrderDTO;
import com.diviso.purchase.service.model.PurchaseOrderModel;

import net.sf.jasperreports.engine.JRException;

import java.time.LocalDate;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PurchaseOrder.
 */
public interface PurchaseOrderService {

    /**
     * Save a purchaseOrder.
     *
     * @param purchaseOrderDTO the entity to save
     * @return the persisted entity
     */
    PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * Get all the purchaseOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PurchaseOrderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PurchaseOrderDTO findOne(Long id);

    /**
     * Delete the "id" purchaseOrder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * create purchaseorder by quotation.
     * @param quotation
     */
    
    
    /*---------------------------------------------------------------
     * EXTRA METHOD
    ----------------------------------------------------------------- */
    
    /**
     * create purchaseorder by quotation.
     * @param quotation
     */
    void save(Quotation quotation);
    
     /**
      * Get the "purchase order" date.
      * @param purchaseDate
      * @param pageable
      * @return the entity
      */

 	Page<PurchaseOrderDTO> findByPurchaseOrder(LocalDate purchaseDate, Pageable pageable);
 	
 	 /**
     * Get the "purchase order" reference.
     * @param reference
     * @param pageable
     * @return the entity
     */

	Page<PurchaseOrderDTO> findByPurchaseOrder(String reference, Pageable pageable);
	
	/**
     * This is a method for issue purchase order by communicating with report service
     * @param id the id of the entity
     */
	String issuePurchaseOrder(Long purchaseOrderId) throws JRException;
	
	/**
	 * This is a method which is used to send individual mail to the customer with attachment
	 * @param id the id of the entity
	 */
	String sendMailWithAttachment(Long purchaseOrderId) throws MessagingException;
	
	 /**
     * Get one purchaseOrder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
	PurchaseOrderModel marshelledFindOne(Long id);
}
