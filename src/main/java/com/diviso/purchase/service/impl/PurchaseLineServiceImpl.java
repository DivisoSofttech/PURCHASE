package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.PurchaseLineService;
import com.diviso.purchase.domain.PurchaseLine;
import com.diviso.purchase.repository.PurchaseLineRepository;
import com.diviso.purchase.service.dto.PurchaseLineDTO;
import com.diviso.purchase.service.mapper.PurchaseLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PurchaseLine.
 */
@Service
@Transactional
public class PurchaseLineServiceImpl implements PurchaseLineService {

    private final Logger log = LoggerFactory.getLogger(PurchaseLineServiceImpl.class);

    private final PurchaseLineRepository purchaseLineRepository;

    private final PurchaseLineMapper purchaseLineMapper;

    public PurchaseLineServiceImpl(PurchaseLineRepository purchaseLineRepository, PurchaseLineMapper purchaseLineMapper) {
        this.purchaseLineRepository = purchaseLineRepository;
        this.purchaseLineMapper = purchaseLineMapper;
    }

    /**
     * Save a purchaseLine.
     *
     * @param purchaseLineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PurchaseLineDTO save(PurchaseLineDTO purchaseLineDTO) {
        log.debug("Request to save PurchaseLine : {}", purchaseLineDTO);
        PurchaseLine purchaseLine = purchaseLineMapper.toEntity(purchaseLineDTO);
        purchaseLine = purchaseLineRepository.save(purchaseLine);
        return purchaseLineMapper.toDto(purchaseLine);
    }

    /**
     * Get all the purchaseLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseLineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseLines");
        return purchaseLineRepository.findAll(pageable)
            .map(purchaseLineMapper::toDto);
    }

    /**
     * Get one purchaseLine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PurchaseLineDTO findOne(Long id) {
        log.debug("Request to get PurchaseLine : {}", id);
        PurchaseLine purchaseLine = purchaseLineRepository.findOne(id);
        return purchaseLineMapper.toDto(purchaseLine);
    }

    /**
     * Delete the purchaseLine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseLine : {}", id);
        purchaseLineRepository.delete(id);
    }
}
