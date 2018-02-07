package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.DeliveriesLineService;
import com.diviso.purchase.domain.DeliveriesLine;
import com.diviso.purchase.repository.DeliveriesLineRepository;
import com.diviso.purchase.service.dto.DeliveriesLineDTO;
import com.diviso.purchase.service.mapper.DeliveriesLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing DeliveriesLine.
 */
@Service
@Transactional
public class DeliveriesLineServiceImpl implements DeliveriesLineService {

    private final Logger log = LoggerFactory.getLogger(DeliveriesLineServiceImpl.class);

    private final DeliveriesLineRepository deliveriesLineRepository;

    private final DeliveriesLineMapper deliveriesLineMapper;

    public DeliveriesLineServiceImpl(DeliveriesLineRepository deliveriesLineRepository, DeliveriesLineMapper deliveriesLineMapper) {
        this.deliveriesLineRepository = deliveriesLineRepository;
        this.deliveriesLineMapper = deliveriesLineMapper;
    }

    /**
     * Save a deliveriesLine.
     *
     * @param deliveriesLineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeliveriesLineDTO save(DeliveriesLineDTO deliveriesLineDTO) {
        log.debug("Request to save DeliveriesLine : {}", deliveriesLineDTO);
        DeliveriesLine deliveriesLine = deliveriesLineMapper.toEntity(deliveriesLineDTO);
        deliveriesLine = deliveriesLineRepository.save(deliveriesLine);
        return deliveriesLineMapper.toDto(deliveriesLine);
    }

    /**
     * Get all the deliveriesLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeliveriesLineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveriesLines");
        return deliveriesLineRepository.findAll(pageable)
            .map(deliveriesLineMapper::toDto);
    }

    /**
     * Get one deliveriesLine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DeliveriesLineDTO findOne(Long id) {
        log.debug("Request to get DeliveriesLine : {}", id);
        DeliveriesLine deliveriesLine = deliveriesLineRepository.findOne(id);
        return deliveriesLineMapper.toDto(deliveriesLine);
    }

    /**
     * Delete the deliveriesLine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveriesLine : {}", id);
        deliveriesLineRepository.delete(id);
    }
}
