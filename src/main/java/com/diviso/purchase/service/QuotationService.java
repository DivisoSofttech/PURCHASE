package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.QuotationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Quotation.
 */
public interface QuotationService {

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    QuotationDTO save(QuotationDTO quotationDTO);

    /**
     * Get all the quotations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationDTO findOne(Long id);

    /**
     * Delete the "id" quotation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}