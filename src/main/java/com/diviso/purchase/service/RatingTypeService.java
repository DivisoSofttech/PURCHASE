package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.RatingTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing RatingType.
 */
public interface RatingTypeService {

    /**
     * Save a ratingType.
     *
     * @param ratingTypeDTO the entity to save
     * @return the persisted entity
     */
    RatingTypeDTO save(RatingTypeDTO ratingTypeDTO);

    /**
     * Get all the ratingTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RatingTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ratingType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RatingTypeDTO findOne(Long id);

    /**
     * Delete the "id" ratingType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
