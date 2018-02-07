package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.StatussService;
import com.diviso.purchase.domain.Statuss;
import com.diviso.purchase.repository.StatussRepository;
import com.diviso.purchase.service.dto.StatussDTO;
import com.diviso.purchase.service.mapper.StatussMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Statuss.
 */
@Service
@Transactional
public class StatussServiceImpl implements StatussService {

    private final Logger log = LoggerFactory.getLogger(StatussServiceImpl.class);

    private final StatussRepository statussRepository;

    private final StatussMapper statussMapper;

    public StatussServiceImpl(StatussRepository statussRepository, StatussMapper statussMapper) {
        this.statussRepository = statussRepository;
        this.statussMapper = statussMapper;
    }

    /**
     * Save a statuss.
     *
     * @param statussDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StatussDTO save(StatussDTO statussDTO) {
        log.debug("Request to save Statuss : {}", statussDTO);
        Statuss statuss = statussMapper.toEntity(statussDTO);
        statuss = statussRepository.save(statuss);
        return statussMapper.toDto(statuss);
    }

    /**
     * Get all the statusses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StatussDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Statusses");
        return statussRepository.findAll(pageable)
            .map(statussMapper::toDto);
    }

    /**
     * Get one statuss by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StatussDTO findOne(Long id) {
        log.debug("Request to get Statuss : {}", id);
        Statuss statuss = statussRepository.findOne(id);
        return statussMapper.toDto(statuss);
    }

    /**
     * Delete the statuss by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Statuss : {}", id);
        statussRepository.delete(id);
    }
}
