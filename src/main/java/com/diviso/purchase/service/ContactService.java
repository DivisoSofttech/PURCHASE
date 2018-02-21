package com.diviso.purchase.service;

import com.diviso.purchase.service.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Contact.
 */
public interface ContactService {

    /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    ContactDTO save(ContactDTO contactDTO);

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ContactDTO> findAll(Pageable pageable);

    /**
     * Get the "id" contact.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ContactDTO findOne(Long id);

    /**
     * Delete the "id" contact.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get the "mailId" contact.
     *
     * @param mailId the mailId of the entity
     * @return the entity
     */

	ContactDTO findByMailId(String mailId);
	
	/**
     * Get the "phoneNumber" contact.
     *
     * @param phoneNumber1 the phoneNumber of the entity
     * @return the entity
     */

	ContactDTO findByPhoneNumber(Long phoneNumber1);
}
