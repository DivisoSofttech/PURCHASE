package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.DeliveryLineService;
import com.diviso.purchase.domain.DeliveryLine;
import com.diviso.purchase.repository.DeliveryLineRepository;
import com.diviso.purchase.service.dto.DeliveryLineDTO;
import com.diviso.purchase.service.mapper.DeliveryLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing DeliveryLine.
 */
@Service
@Transactional
public class DeliveryLineServiceImpl implements DeliveryLineService {

    private final Logger log = LoggerFactory.getLogger(DeliveryLineServiceImpl.class);

    private final DeliveryLineRepository deliveryLineRepository;

    private final DeliveryLineMapper deliveryLineMapper;

    public DeliveryLineServiceImpl(DeliveryLineRepository deliveryLineRepository, DeliveryLineMapper deliveryLineMapper) {
        this.deliveryLineRepository = deliveryLineRepository;
        this.deliveryLineMapper = deliveryLineMapper;
    }

    /**
     * Save a deliveryLine.
     *
     * @param deliveryLineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeliveryLineDTO save(DeliveryLineDTO deliveryLineDTO) {
        log.debug("Request to save DeliveryLine : {}", deliveryLineDTO);
        DeliveryLine deliveryLine = deliveryLineMapper.toEntity(deliveryLineDTO);
        deliveryLine = deliveryLineRepository.save(deliveryLine);
        return deliveryLineMapper.toDto(deliveryLine);
    }

    /**
     * Get all the deliveryLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryLineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryLines");
        return deliveryLineRepository.findAll(pageable)
            .map(deliveryLineMapper::toDto);
    }

    /**
     * Get one deliveryLine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DeliveryLineDTO findOne(Long id) {
        log.debug("Request to get DeliveryLine : {}", id);
        DeliveryLine deliveryLine = deliveryLineRepository.findOne(id);
        return deliveryLineMapper.toDto(deliveryLine);
    }

    /**
     * Delete the deliveryLine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryLine : {}", id);
        deliveryLineRepository.delete(id);
    }
}
