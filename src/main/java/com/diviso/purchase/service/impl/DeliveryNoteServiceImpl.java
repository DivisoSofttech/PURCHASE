package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.DeliveryNoteService;
import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.repository.DeliveryNoteRepository;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import com.diviso.purchase.service.mapper.DeliveryNoteMapper;

import java.io.File;
import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Service Implementation for managing DeliveryNote.
 */
@Service
@Transactional
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final Logger log = LoggerFactory.getLogger(DeliveryNoteServiceImpl.class);

    private final DeliveryNoteRepository deliveryNoteRepository;

    private final DeliveryNoteMapper deliveryNoteMapper;
    
    @Autowired
   	public JavaMailSender emailSender;


    public DeliveryNoteServiceImpl(DeliveryNoteRepository deliveryNoteRepository, DeliveryNoteMapper deliveryNoteMapper) {
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.deliveryNoteMapper = deliveryNoteMapper;
    }

    /**
     * Save a deliveryNote.
     *
     * @param deliveryNoteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeliveryNoteDTO save(DeliveryNoteDTO deliveryNoteDTO) {
        log.debug("Request to save DeliveryNote : {}", deliveryNoteDTO);
        DeliveryNote deliveryNote = deliveryNoteMapper.toEntity(deliveryNoteDTO);
        deliveryNote = deliveryNoteRepository.save(deliveryNote);
        return deliveryNoteMapper.toDto(deliveryNote);
    }

    /**
     * Get all the deliveryNotes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryNoteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryNotes");
        return deliveryNoteRepository.findAll(pageable)
            .map(deliveryNoteMapper::toDto);
    }

    /**
     * Get one deliveryNote by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DeliveryNoteDTO findOne(Long id) {
        log.debug("Request to get DeliveryNote : {}", id);
        DeliveryNote deliveryNote = deliveryNoteRepository.findOneWithEagerRelationships(id);
        return deliveryNoteMapper.toDto(deliveryNote);
    }

    /**
     * Delete the deliveryNote by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeliveryNote : {}", id);
        deliveryNoteRepository.delete(id);
    }
    
    /**
     * Get all the deliveryNotes by reference.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */

	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryNoteDTO> findAllByReference(String reference, Pageable pageable) {
		// TODO Auto-generated method stub
		 log.debug("Request to get all DeliveryNotes");
	        return deliveryNoteRepository.findAllByReference(reference,pageable)
	            .map(deliveryNoteMapper::toDto);
	}
	
	 /**
     * Get all the deliveryNotes by delivered date.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */

	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryNoteDTO> findAllByDeliveredDate(LocalDate deliveredDate, Pageable pageable) {
		// TODO Auto-generated method stub
		log.debug("Request to get all DeliveryNotes");
        return deliveryNoteRepository.findAllByDeliveredDate(deliveredDate,pageable)
            .map(deliveryNoteMapper::toDto);
	}
	
	 /**
     * Get all the deliveryNotes by delivered date between.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */

	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryNoteDTO> findAllByDeliveredDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
		// TODO Auto-generated method stub
		log.debug("Request to get all DeliveryNotes");
        return deliveryNoteRepository.findAllByDeliveredDateBetween(startDate,endDate,pageable)
            .map(deliveryNoteMapper::toDto);
	}
	
	 /**
     * Get all the deliveryNotes by supplier id.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */

	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryNoteDTO> findAllBySupplier_Id(Long supplierId, Pageable pageable) {
		// TODO Auto-generated method stub
		log.debug("Request to get all DeliveryNotes");
        return deliveryNoteRepository.findAllBySupplier_Id(supplierId,pageable)
            .map(deliveryNoteMapper::toDto);
	}

	 /**
     * Get all the deliveryNotes by order reference.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryNoteDTO> findAllByOrderReference(String orderReference, Pageable pageable) {
		// TODO Auto-generated method stub
		log.debug("Request to get all DeliveryNotes");
        return deliveryNoteRepository.findAllByOrderReference(orderReference,pageable)
            .map(deliveryNoteMapper::toDto);

	}
//  Email

	// -------------------------------------------------------------------------------------
	/**
	 * This is a method which is used to send individual mail to the supplier
	 * @param to,subject,text
	 * 
	 */
	@PostMapping("/customers/sendmail/{to}/{subject}/{text}")
	@Timed
	public String sendMail(@PathVariable String to,@PathVariable String subject,@PathVariable String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);

		return "Mail Successfully sent..";
	}
	
	
	
	/**
	 * This is a method which is used to send individual mail to the supplier with attachment
	 * @param to,subject,text
	 * 
	 */
	@PostMapping("/customers/sendmailwithattachments")
	@Timed
	public String sendMailWithAttachments(@PathVariable String to,@PathVariable String subject,@PathVariable String text
											 ) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);

		FileSystemResource file = new FileSystemResource(new File("C:/Users/ATHIRA/Desktop/PURCHASE/attachments/pic.png"));
		helper.addAttachment("Invoice", file);

		emailSender.send(message);

		return "Mail Successfully sent..";
	}
	
	/**
	 * This is a method which is used update the inventory after the delivery
	 * 
	 * 
	 */

	@Override
	public String updateInventory() {
		// TODO Auto-generated method stub
		return null;
	}
  
}
