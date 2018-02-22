package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.QuotationDTO;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Quotation.
 */
public interface QuotationService {

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    QuotationDTO save(QuotationDTO quotationDTO);

    /**
     * Get all the quotations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" quotation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuotationDTO findOne(Long id);

    /**
     * Delete the "id" quotation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


	Page<QuotationDTO> findByReference(String reference, Pageable pageable);

	Page<QuotationDTO> findBySupplierId(Long id, Pageable pageable);

	Page<QuotationDTO> findBySupplierReference(String reference, Pageable pageable);

	Page<QuotationDTO> findByStatussId(Long id, Pageable pageable);

	Page<QuotationDTO> findByStatussName(String name, Pageable pageable);

	
	Page<QuotationDTO> findByIssuedDate(LocalDate date, Pageable pageable);

	Page<QuotationDTO> findByIssuedDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	Page<QuotationDTO> findByIssuedDateAfter(LocalDate localDate, Pageable pageable);

	Page<QuotationDTO> findByIssuedDateBefore(LocalDate localDate, Pageable pageable);

	Page<QuotationDTO> findByQuotationLineCount(int count, Pageable pageable);

}
