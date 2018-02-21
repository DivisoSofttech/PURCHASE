package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.RatingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Rating.
 */
public interface RatingService {

    /**
     * Save a rating.
     *
     * @param ratingDTO the entity to save
     * @return the persisted entity
     */
    RatingDTO save(RatingDTO ratingDTO);

    /**
     * Get all the ratings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RatingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" rating.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RatingDTO findOne(Long id);

    /**
     * Delete the "id" rating.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the ratings by rating.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<RatingDTO> findAllByRating(Integer rating, Pageable pageable);

	/**
     * Get all the ratings by rating greater than.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<RatingDTO> findAllByRatingGreaterThan(Integer rating, Pageable pageable);

	/**
     * Get all the ratings by rating less than.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<RatingDTO> findAllByRatingLessThan(Integer rating, Pageable pageable);
}
