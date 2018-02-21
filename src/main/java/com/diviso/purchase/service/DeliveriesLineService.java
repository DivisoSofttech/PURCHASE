package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.DeliveriesLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DeliveriesLine.
 */
public interface DeliveriesLineService {

    /**
     * Save a deliveriesLine.
     *
     * @param deliveriesLineDTO the entity to save
     * @return the persisted entity
     */
    DeliveriesLineDTO save(DeliveriesLineDTO deliveriesLineDTO);

    /**
     * Get all the deliveriesLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DeliveriesLineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveriesLine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DeliveriesLineDTO findOne(Long id);

    /**
     * Delete the "id" deliveriesLine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the deliveriesLines by reference.
     *
     * @param pageable the pagination information
     * @param string reference
     * @return the list of entities
     *
     * */
	Page<DeliveriesLineDTO> findAllByReference(String reference, Pageable pageable);

/**
     * Get all the deliveriesLines by price greater than equal.
     *
     * @param pageable the pagination information
     * @param double price
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByPriceGreaterThanEqual(Double price, Pageable pageable);

/**
     * Get all the deliveriesLines by price less than equal .
     *
     * @param pageable the pagination information
     * @param double price
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByPriceLessThanEqual(Double price, Pageable pageable);

/**
     * Get all the deliveriesLines by price.
     *
     * @param pageable the pagination information
     * @param double price
     * @return the list of entities
     **/
	Page<DeliveriesLineDTO> findAllByPrice(Double price, Pageable pageable);

/**
     * Get all the deliveriesLines by tax greater than equal.
     *
     * @param pageable the pagination information
     * @param double tax
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByTaxGreaterThanEqual(Double tax, Pageable pageable);

/**
     * Get all the deliveriesLines by tax less than equal.
     *
     * @param pageable the pagination information
     * @param double tax
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByTaxLessThanEqual(Double tax, Pageable pageable);
	/**
     * Get all the deliveriesLines by tax.
     *
     * @param pageable the pagination information
     * @param double tax
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByTax(Double tax, Pageable pageable);

	/**
     * Get all the deliveriesLines by quantity.
     *
     * @param pageable the pagination information
     * @param double quantity
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByQuantity(Double quantity, Pageable pageable);

	/**
     * Get all the deliveriesLines by quantity greater than equal.
     *
     * @param pageable the pagination information
     * @param double quantity
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByQuantityGreaterThanEqual(Double quantity, Pageable pageable);

	/**
     * Get all the deliveriesLines by quantity less than equal.
     *
     * @param pageable the pagination information
     * @param double quantity
     * @return the list of entities
     * */
	Page<DeliveriesLineDTO> findAllByQuantityLessThanEqual(Double quantity, Pageable pageable);
}
