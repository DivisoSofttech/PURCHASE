package com.diviso.purchase.repository;

import com.diviso.purchase.domain.Statuss;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Statuss entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatussRepository extends JpaRepository<Statuss, Long> {

}
