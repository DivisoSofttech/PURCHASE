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
	/**
	 * Spring Data JPA repository for the PurchaseOrder entity.
	 */
	Page<PurchaseOrder> findByPurchaseDate( LocalDate purchaseDate ,Pageable pageable);
	Page<PurchaseOrder> findByReference( String reference ,Pageable pageable);
	/**
	 * Spring Data JPA repository for the PurchaseOrder entity.
	 * Use has a relationship. 
	 */
	Page<PurchaseOrder> findBySupplier_Id(Long id,Pageable pageable);
	Page<PurchaseOrder> findBySupplier_Reference(String reference,Pageable pageable );
	Page<PurchaseOrder> findByPurchaseDateBetween(LocalDate startDate,LocalDate endDate,Pageable pageable);
	Page<PurchaseOrder> findByStatuss_Id(Long id,Pageable pageable);
	Page<PurchaseOrder> findByStatuss_Name(String name,Pageable pageable);
	Page<PurchaseOrder> findByPurchaseLines_Id(Long id,Pageable pageable);
	Page<PurchaseOrder> findByPurchaseLines_ProductReference(String productReference,Pageable pageable);
	
}
