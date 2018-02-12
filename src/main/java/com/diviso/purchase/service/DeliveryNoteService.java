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


	Page<DeliveryNoteDTO> findAllByOrderReference(String orderReference, Pageable pageable);

	Page<DeliveryNoteDTO> findAllBySupplier_Id(Long supplierId, Pageable pageable);

	Page<DeliveryNoteDTO> findAllByPurchaseDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	Page<DeliveryNoteDTO> findAllByPurchaseDate(LocalDate purchaseDate, Pageable pageable);

	Page<DeliveryNoteDTO> findAllByReference(String reference, Pageable pageable);
}
