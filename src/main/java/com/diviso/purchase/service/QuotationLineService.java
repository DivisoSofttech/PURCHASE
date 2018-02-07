package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.QuotationLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationLine.
 */
public interface QuotationLineService {

    /**
     * Save a quotationLine.
     *
     * @param quotationLineDTO the entity to save
     * @return the persisted entity
     */
    QuotationLineDTO save(QuotationLineDTO quotationLineDTO);

    /**
     * Get all the quotationLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationLineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationLine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationLineDTO findOne(Long id);

    /**
     * Delete the "id" quotationLine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
