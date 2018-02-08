package com.diviso.purchase.repository;

import com.diviso.purchase.domain.QuotationLine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotationLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationLineRepository extends JpaRepository<QuotationLine, Long> {

}
