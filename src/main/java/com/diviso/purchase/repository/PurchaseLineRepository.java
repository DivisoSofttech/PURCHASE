package com.diviso.purchase.repository;

import com.diviso.purchase.domain.PurchaseLine;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PurchaseLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Long> {
	
	/*--------------------------------------------------------------
	 * EXTRA METHOD
	 ---------------------------------------------------------------*/
	Page<PurchaseLine> findByProductReference( String productReference ,Pageable pageable);
	Page<PurchaseLine> findByProductPrice( Double productPrice ,Pageable pageable);
	Page<PurchaseLine> findByProductTax( Double productTax ,Pageable pageable);
	Page<PurchaseLine> findByQuantity( Double quantity ,Pageable pageable);
}
