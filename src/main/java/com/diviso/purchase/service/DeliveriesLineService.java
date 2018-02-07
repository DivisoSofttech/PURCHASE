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
}
