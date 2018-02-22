package com.diviso.purchase.repository;

import com.diviso.purchase.domain.DeliveriesLine;
import com.diviso.purchase.service.dto.DeliveriesLineDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DeliveriesLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveriesLineRepository extends JpaRepository<DeliveriesLine, Long> {

	Page<DeliveriesLine> findAllByReference(String reference, Pageable pageable);

	Page<DeliveriesLine> findAllByPriceGreaterThanEqual(Double price, Pageable pageable);

	Page<DeliveriesLine> findAllByPriceLessThanEqual(Double price, Pageable pageable);

	Page<DeliveriesLine> findAllByPrice(Double price, Pageable pageable);

	Page<DeliveriesLine> findAllByTaxGreaterThanEqual(Double tax, Pageable pageable);

	Page<DeliveriesLine> findAllByTaxLessThanEqual(Double tax, Pageable pageable);

	Page<DeliveriesLine> findAllByTax(Double tax, Pageable pageable);

	Page<DeliveriesLine> findAllByQuantity(Double quantity, Pageable pageable);

	Page<DeliveriesLine> findAllByQuantityGreaterThanEqual(Double quantity, Pageable pageable);

	Page<DeliveriesLine> findAllByQuantityLessThanEqual(Double quantity, Pageable pageable);

}
