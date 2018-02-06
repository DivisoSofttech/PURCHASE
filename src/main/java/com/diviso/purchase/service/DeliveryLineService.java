package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.DeliveryLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DeliveryLine.
 */
public interface DeliveryLineService {

    /**
     * Save a deliveryLine.
     *
     * @param deliveryLineDTO the entity to save
     * @return the persisted entity
     */
    DeliveryLineDTO save(DeliveryLineDTO deliveryLineDTO);

    /**
     * Get all the deliveryLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DeliveryLineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryLine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DeliveryLineDTO findOne(Long id);

    /**
     * Delete the "id" deliveryLine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
