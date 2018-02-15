package com.diviso.purchase.repository;

import com.diviso.purchase.domain.Contact;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Contact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	Contact findByMailId(String mailId);
	
	Contact findByPhoneNumber(Long phoneNumber1);

}
