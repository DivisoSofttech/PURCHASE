package com.diviso.purchase.repository;

import com.diviso.purchase.domain.Quotation;
import com.diviso.purchase.service.dto.QuotationDTO;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Quotation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {

	Page<Quotation> findByReference(String reference, Pageable pageable);

	Page<Quotation> findBySupplier_Id(Long id, Pageable pageable);

	Page<Quotation> findBySupplier_Reference(String reference, Pageable pageable);

	Page<Quotation> findByStatuss_Id(Long id, Pageable pageable);

	Page<Quotation> findByStatuss_Name(String name, Pageable pageable);

	

}
