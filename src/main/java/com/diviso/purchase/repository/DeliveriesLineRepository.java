package com.diviso.purchase.repository;

import com.diviso.purchase.domain.DeliveriesLine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DeliveriesLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveriesLineRepository extends JpaRepository<DeliveriesLine, Long> {

}
