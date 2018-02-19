package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.QuotationService;
import com.diviso.purchase.domain.Quotation;
import com.diviso.purchase.repository.QuotationRepository;
import com.diviso.purchase.service.dto.QuotationDTO;
import com.diviso.purchase.service.mapper.QuotationMapper;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Quotation.
 */
@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

    private final Logger log = LoggerFactory.getLogger(QuotationServiceImpl.class);

    private final QuotationRepository quotationRepository;

    private final QuotationMapper quotationMapper;

    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
    }

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationDTO save(QuotationDTO quotationDTO) {
        log.debug("Request to save Quotation : {}", quotationDTO);
        Quotation quotation = quotationMapper.toEntity(quotationDTO);
        quotation = quotationRepository.save(quotation);
        return quotationMapper.toDto(quotation);
    }

    /**
     * Get all the quotations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quotations");
        return quotationRepository.findAll(pageable)
            .map(quotationMapper::toDto);
    }

    /**
     * Get one quotation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationDTO findOne(Long id) {
        log.debug("Request to get Quotation : {}", id);
        Quotation quotation = quotationRepository.findOne(id);
        return quotationMapper.toDto(quotation);
    }

    /**
     * Delete the quotation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quotation : {}", id);
        quotationRepository.delete(id);
    }

	@Override
	public Page<QuotationDTO> findByIssuedDate(LocalDate date, Pageable pageable) {
		log.debug("Request to get Quotation by Date : {}", date);
		return quotationRepository.findByIssuedDate(date,pageable).map(quotationMapper::toDto);
	}
}
