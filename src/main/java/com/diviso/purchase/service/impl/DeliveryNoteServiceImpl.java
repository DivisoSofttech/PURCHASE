package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.DeliveryNoteService;
import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.repository.DeliveryNoteRepository;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import com.diviso.purchase.service.mapper.DeliveryNoteMapper;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing DeliveryNote.
 */
@Service
@Transactional
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final Logger log = LoggerFactory.getLogger(DeliveryNoteServiceImpl.class);

    private final DeliveryNoteRepository deliveryNoteRepository;

    private final DeliveryNoteMapper deliveryNoteMapper;

    public DeliveryNoteServiceImpl(DeliveryNoteRepository deliveryNoteRepository, DeliveryNoteMapper deliveryNoteMapper) {
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.deliveryNoteMapper = deliveryNoteMapper;
    }

    /**
     * Save a deliveryNote.
     *
     * @param deliveryNoteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeliveryNoteDTO save(DeliveryNoteDTO deliveryNoteDTO) {
        log.debug("Request to save DeliveryNote : {}", deliveryNoteDTO);
        DeliveryNote deliveryNote = deliveryNoteMapper.toEntity(deliveryNoteDTO);
        deliveryNote = deliveryNoteRepository.save(deliveryNote);
        return deliveryNoteMapper.toDto(deliveryNote);
    }

    /**
     * Get all the deliveryNotes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryNoteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryNotes");
        return deliveryNoteRepository.findAll(pageable)
            .map(deliveryNoteMapper::toDto);
    }

    /**
     * Get one deliveryNote by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DeliveryNoteDTO findOne(Long id) {
        log.debug("Request to get DeliveryNote : {}", id);
        DeliveryNote deliveryNote = deliveryNoteRepository.findOneWithEagerRelationships(id);
        return deliveryNoteMapper.toDto(deliveryNote);
    }

    /**
     * Delete the deliveryNote by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryNote : {}", id);
        deliveryNoteRepository.delete(id);
    }

    /**
     * Get all the deliveryNotes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */

	@Override
	public Page<DeliveryNoteDTO> findByPurchaseDate(LocalDate purchaseDate, Pageable pageable) {
		// TODO Auto-generated method stub
		 log.debug("Request to get all DeliveryNotes: {}",purchaseDate);
	        return deliveryNoteRepository.findByPurchaseDate(purchaseDate,pageable)
	            .map(deliveryNoteMapper::toDto);
	}
}
