package com.diviso.purchase.repository;

import com.diviso.purchase.domain.Rating;
import com.diviso.purchase.service.dto.RatingDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Rating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	Page<Rating> findAllByRatings(Integer rating, Pageable pageable);

	Page<Rating> findAllByRatingsGreaterThan(Integer rating, Pageable pageable);

	Page<Rating> findAllByRatingsLessThan(Integer rating, Pageable pageable);

}
