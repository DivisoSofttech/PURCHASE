package com.diviso.purchase.repository;

import com.diviso.purchase.domain.PurchaseLine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PurchaseLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Long> {

}
