package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.SupplierService;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.repository.SupplierRepository;
import com.diviso.purchase.service.dto.SupplierDTO;
import com.diviso.purchase.service.mapper.SupplierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Supplier.
 */
@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final Logger log = LoggerFactory.getLogger(SupplierServiceImpl.class);

    private final SupplierRepository supplierRepository;

    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    /**
     * Save a supplier.
     *
     * @param supplierDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SupplierDTO save(SupplierDTO supplierDTO) {
        log.debug("Request to save Supplier : {}", supplierDTO);
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(supplier);
    }

    /**
     * Get all the suppliers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SupplierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Suppliers");
        return supplierRepository.findAll(pageable)
            .map(supplierMapper::toDto);
    }

    /**
     * Get one supplier by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SupplierDTO findOne(Long id) {
        log.debug("Request to get Supplier : {}", id);
        Supplier supplier = supplierRepository.findOne(id);
        return supplierMapper.toDto(supplier);
    }

    /**
     * Delete the supplier by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Supplier : {}", id);
        supplierRepository.delete(id);
    }    
        /*--------------------------------------------------------------
         * EXTRA METHOD
         * -------------------------------------------------------------*/
    	
        /**
         * Get suppliers by reference
         * @param suppliers the reference of the entity
         */
        
        @Override
        @Transactional(readOnly = true)
    	public SupplierDTO findByReference(String reference) {
    		// TODO Auto-generated method stub
        	log.debug("Request to get Supplier : {}", reference);
        	 Supplier supplier = supplierRepository.findByReference(reference);
             return supplierMapper.toDto(supplier);
    	}
    
    	@Override
    	@Transactional(readOnly = true)
    	public Page<SupplierDTO> findByFirstName(String firstName, Pageable pageable) {
    		// TODO Auto-generated method stub
    		log.debug("Request to get suppliers : {}", firstName);
    		return supplierRepository.findByFirstName(firstName,pageable).map(supplierMapper::toDto);
    	}
     }


