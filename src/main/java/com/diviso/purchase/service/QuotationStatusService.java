package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.QuotationStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing QuotationStatus.
 */
public interface QuotationStatusService {

    /**
     * Save a quotationStatus.
     *
     * @param quotationStatusDTO the entity to save
     * @return the persisted entity
     */
    QuotationStatusDTO save(QuotationStatusDTO quotationStatusDTO);

    /**
     * Get all the quotationStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotationStatus.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationStatusDTO findOne(Long id);

    /**
     * Delete the "id" quotationStatus.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
