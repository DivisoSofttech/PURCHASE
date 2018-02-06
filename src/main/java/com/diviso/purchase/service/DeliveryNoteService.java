package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.DeliveryNoteDTO;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

	Page<DeliveryNoteDTO> findByPurchaseDate(LocalDate purchaseDate, Pageable pageable);
}
