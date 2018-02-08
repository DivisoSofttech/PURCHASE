package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.PurchaseLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PurchaseLine.
 */
public interface PurchaseLineService {

    /**
     * Save a purchaseLine.
     *
     * @param purchaseLineDTO the entity to save
     * @return the persisted entity
     */
    PurchaseLineDTO save(PurchaseLineDTO purchaseLineDTO);

    /**
     * Get all the purchaseLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PurchaseLineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseLine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PurchaseLineDTO findOne(Long id);

    /**
     * Delete the "id" purchaseLine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /*----------------------------------
     *EXTRA METHOD 
     -----------------------------------*/ 
    /**
     * Get the "purchase line" reference.
     * @param reference
     * @param pageable
     * @return the entity
     */

	Page<PurchaseLineDTO> findByPurchaseLine(String productReference, Pageable pageable);
	/**
     * Get the "purchase line" price.
     * @param price
     * @param pageable
     * @return the entity
     */

	Page<PurchaseLineDTO> findByPurchaseLinePrice(Double productPrice, Pageable pageable);
	/**
     * Get the "purchase line" productTax.
     * @param tax
     * @param pageable
     * @return the entity
     */

	Page<PurchaseLineDTO> findByPurchaseLineTax(Double productTax, Pageable pageable);
	/**
     * Get the "purchase line" productQuantity.
     * @param tax
     * @param pageable
     * @return the entity
     */

	Page<PurchaseLineDTO> findByPurchaseLineQuantity(Double quantity, Pageable pageable);
}
