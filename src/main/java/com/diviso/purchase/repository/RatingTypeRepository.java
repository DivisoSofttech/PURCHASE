package com.diviso.purchase.repository;

import com.diviso.purchase.domain.RatingType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RatingType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingTypeRepository extends JpaRepository<RatingType, Long> {

}
