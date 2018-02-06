package com.diviso.purchase.repository;

import com.diviso.purchase.domain.DeliveryLine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DeliveryLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryLineRepository extends JpaRepository<DeliveryLine, Long> {

}
