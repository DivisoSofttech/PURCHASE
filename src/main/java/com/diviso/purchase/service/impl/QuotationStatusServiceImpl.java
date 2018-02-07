package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.QuotationStatusService;
import com.diviso.purchase.domain.QuotationStatus;
import com.diviso.purchase.repository.QuotationStatusRepository;
import com.diviso.purchase.service.dto.QuotationStatusDTO;
import com.diviso.purchase.service.mapper.QuotationStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing QuotationStatus.
 */
@Service
@Transactional
public class QuotationStatusServiceImpl implements QuotationStatusService {

    private final Logger log = LoggerFactory.getLogger(QuotationStatusServiceImpl.class);

    private final QuotationStatusRepository quotationStatusRepository;

    private final QuotationStatusMapper quotationStatusMapper;

    public QuotationStatusServiceImpl(QuotationStatusRepository quotationStatusRepository, QuotationStatusMapper quotationStatusMapper) {
        this.quotationStatusRepository = quotationStatusRepository;
        this.quotationStatusMapper = quotationStatusMapper;
    }

    /**
     * Save a quotationStatus.
     *
     * @param quotationStatusDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationStatusDTO save(QuotationStatusDTO quotationStatusDTO) {
        log.debug("Request to save QuotationStatus : {}", quotationStatusDTO);
        QuotationStatus quotationStatus = quotationStatusMapper.toEntity(quotationStatusDTO);
        quotationStatus = quotationStatusRepository.save(quotationStatus);
        return quotationStatusMapper.toDto(quotationStatus);
    }

    /**
     * Get all the quotationStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuotationStatuses");
        return quotationStatusRepository.findAll(pageable)
            .map(quotationStatusMapper::toDto);
    }

    /**
     * Get one quotationStatus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuotationStatusDTO findOne(Long id) {
        log.debug("Request to get QuotationStatus : {}", id);
        QuotationStatus quotationStatus = quotationStatusRepository.findOne(id);
        return quotationStatusMapper.toDto(quotationStatus);
    }

    /**
     * Delete the quotationStatus by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuotationStatus : {}", id);
        quotationStatusRepository.delete(id);
    }
}
