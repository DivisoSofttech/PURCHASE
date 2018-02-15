package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.ContactService;
import com.diviso.purchase.domain.Contact;
import com.diviso.purchase.repository.ContactRepository;
import com.diviso.purchase.service.dto.ContactDTO;
import com.diviso.purchase.service.mapper.ContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Contact.
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        log.debug("Request to save Contact : {}", contactDTO);
        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        return contactMapper.toDto(contact);
    }

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable)
            .map(contactMapper::toDto);
    }

    /**
     * Get one contact by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ContactDTO findOne(Long id) {
        log.debug("Request to get Contact : {}", id);
        Contact contact = contactRepository.findOne(id);
        return contactMapper.toDto(contact);
    }

    /**
     * Delete the contact by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contact : {}", id);
        contactRepository.delete(id);
    }
    
    /**
     * Get one contact by mailId.
     *
     * @param mailId the mailId of the entity
     * @return the entity
     */
    
	@Override
	@Transactional(readOnly = true)
	public ContactDTO findByMailId(String mailId) {
		// TODO Auto-generated method stub
		log.debug("Request to get suppliers : {}", mailId);
		Contact contact=contactRepository.findByMailId(mailId);
		return contactMapper.toDto(contact);
	}

	/**
     * Get one contact by phoneNumber.
     *
     * @param phoneNumber1 the phoneNumber of the entity
     * @return the entity
     */
	
	@Override
	@Transactional(readOnly = true)
	public ContactDTO findByPhoneNumber(Long phoneNumber1) {
		// TODO Auto-generated method stub
		log.debug("Request to get suppliers : {}", phoneNumber1);
		Contact contact=contactRepository.findByPhoneNumber(phoneNumber1);
		return contactMapper.toDto(contact);
	}
}
