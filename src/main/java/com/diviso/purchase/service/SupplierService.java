package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.SupplierDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Supplier.
 */
public interface SupplierService {

    /**
     * Save a supplier.
     *
     * @param supplierDTO the entity to save
     * @return the persisted entity
     */
    SupplierDTO save(SupplierDTO supplierDTO);

    /**
     * Get all the suppliers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SupplierDTO> findAll(Pageable pageable);

    /**
     * Get the "id" supplier.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SupplierDTO findOne(Long id);

    /**
     * Delete the "id" supplier.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "reference" supplier.
     *
     * @param reference of the entity
     * @param pageable
     * @return the entity
     */
    
    SupplierDTO findByReference(String reference);
    	/**
         * Get the "firstName" supplier.
         *
         * @param firstName of the entity
         * @param pageable
         * @return the entity
         */
    
    	Page<SupplierDTO> findByFirstName(String firstName, Pageable pageable);

}
