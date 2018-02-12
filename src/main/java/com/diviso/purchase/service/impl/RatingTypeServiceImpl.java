package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.RatingTypeService;
import com.diviso.purchase.domain.RatingType;
import com.diviso.purchase.repository.RatingTypeRepository;
import com.diviso.purchase.service.dto.RatingTypeDTO;
import com.diviso.purchase.service.mapper.RatingTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing RatingType.
 */
@Service
@Transactional
public class RatingTypeServiceImpl implements RatingTypeService {

    private final Logger log = LoggerFactory.getLogger(RatingTypeServiceImpl.class);

    private final RatingTypeRepository ratingTypeRepository;

    private final RatingTypeMapper ratingTypeMapper;

    public RatingTypeServiceImpl(RatingTypeRepository ratingTypeRepository, RatingTypeMapper ratingTypeMapper) {
        this.ratingTypeRepository = ratingTypeRepository;
        this.ratingTypeMapper = ratingTypeMapper;
    }

    /**
     * Save a ratingType.
     *
     * @param ratingTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RatingTypeDTO save(RatingTypeDTO ratingTypeDTO) {
        log.debug("Request to save RatingType : {}", ratingTypeDTO);
        RatingType ratingType = ratingTypeMapper.toEntity(ratingTypeDTO);
        ratingType = ratingTypeRepository.save(ratingType);
        return ratingTypeMapper.toDto(ratingType);
    }

    /**
     * Get all the ratingTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RatingTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RatingTypes");
        return ratingTypeRepository.findAll(pageable)
            .map(ratingTypeMapper::toDto);
    }

    /**
     * Get one ratingType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RatingTypeDTO findOne(Long id) {
        log.debug("Request to get RatingType : {}", id);
        RatingType ratingType = ratingTypeRepository.findOne(id);
        return ratingTypeMapper.toDto(ratingType);
    }

    /**
     * Delete the ratingType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RatingType : {}", id);
        ratingTypeRepository.delete(id);
    }
}
