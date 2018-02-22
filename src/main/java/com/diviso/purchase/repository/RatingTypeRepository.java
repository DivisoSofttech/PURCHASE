package com.diviso.purchase.repository;

import com.diviso.purchase.domain.RatingType;
import com.diviso.purchase.service.dto.RatingTypeDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RatingType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingTypeRepository extends JpaRepository<RatingType, Long> {

	Page<RatingType> findAllByReference(String reference, Pageable pageable);

	Page<RatingType> findAllByType(String type, Pageable pageable);

}
