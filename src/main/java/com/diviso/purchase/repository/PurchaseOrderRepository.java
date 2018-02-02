package com.diviso.purchase.repository;

import com.diviso.purchase.domain.PurchaseOrder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PurchaseOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	Page<PurchaseOrder>findByPurchaseDate( LocalDate purchaseDate ,Pageable pageable);

}
