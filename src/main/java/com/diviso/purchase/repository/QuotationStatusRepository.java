package com.diviso.purchase.repository;

import com.diviso.purchase.domain.QuotationStatus;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationStatusRepository extends JpaRepository<QuotationStatus, Long> {

}
