package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.StatussDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Statuss.
 */
public interface StatussService {

    /**
     * Save a statuss.
     *
     * @param statussDTO the entity to save
     * @return the persisted entity
     */
    StatussDTO save(StatussDTO statussDTO);

    /**
     * Get all the statusses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StatussDTO> findAll(Pageable pageable);

    /**
     * Get the "id" statuss.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StatussDTO findOne(Long id);

    /**
     * Delete the "id" statuss.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
