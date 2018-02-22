package com.diviso.purchase.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.DeliveryNoteService;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;

/**
 * REST controller for managing DeliveryNote.
 */
@RestController
@RequestMapping("/api")
public class DeliveryNoteResource {

	private final Logger log = LoggerFactory.getLogger(DeliveryNoteResource.class);

	private static final String ENTITY_NAME = "deliveryNote";

	private final DeliveryNoteService deliveryNoteService;

	@Autowired
	public JavaMailSender emailSender;

	public DeliveryNoteResource(DeliveryNoteService deliveryNoteService) {
		this.deliveryNoteService = deliveryNoteService;
	}

	/**
	 * POST /delivery-notes : Create a new deliveryNote.
	 *
	 * @param deliveryNoteDTO
	 *            the deliveryNoteDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new deliveryNoteDTO, or with status 400 (Bad Request) if the
	 *         deliveryNote has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/delivery-notes")
	@Timed
	public ResponseEntity<DeliveryNoteDTO> createDeliveryNote(@RequestBody DeliveryNoteDTO deliveryNoteDTO)
			throws URISyntaxException {
		log.debug("REST request to save DeliveryNote : {}", deliveryNoteDTO);
		if (deliveryNoteDTO.getId() != null) {
			throw new BadRequestAlertException("A new deliveryNote cannot already have an ID", ENTITY_NAME, "idexists");
		}
		DeliveryNoteDTO result = deliveryNoteService.save(deliveryNoteDTO);
		return ResponseEntity.created(new URI("/api/delivery-notes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /delivery-notes : Updates an existing deliveryNote.
	 *
	 * @param deliveryNoteDTO
	 *            the deliveryNoteDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         deliveryNoteDTO, or with status 400 (Bad Request) if the
	 *         deliveryNoteDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the deliveryNoteDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/delivery-notes")
	@Timed
	public ResponseEntity<DeliveryNoteDTO> updateDeliveryNote(@RequestBody DeliveryNoteDTO deliveryNoteDTO)
			throws URISyntaxException {
		log.debug("REST request to update DeliveryNote : {}", deliveryNoteDTO);
		if (deliveryNoteDTO.getId() == null) {
			return createDeliveryNote(deliveryNoteDTO);
		}
		DeliveryNoteDTO result = deliveryNoteService.save(deliveryNoteDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryNoteDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /delivery-notes : get all the deliveryNotes.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         deliveryNotes in body
	 */
	@GetMapping("/delivery-notes")
	@Timed
	public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotes(Pageable pageable) {
		log.debug("REST request to get a page of DeliveryNotes");
		Page<DeliveryNoteDTO> page = deliveryNoteService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /delivery-notes/:id : get the "id" deliveryNote.
	 *
	 * @param id
	 *            the id of the deliveryNoteDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         deliveryNoteDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/delivery-notes/{id}")
	@Timed
	public ResponseEntity<DeliveryNoteDTO> getDeliveryNote(@PathVariable Long id) {
		log.debug("REST request to get DeliveryNote : {}", id);
		DeliveryNoteDTO deliveryNoteDTO = deliveryNoteService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(deliveryNoteDTO));
	}

	/**
	 * DELETE /delivery-notes/:id : delete the "id" deliveryNote.
	 *
	 * @param id
	 *            the id of the deliveryNoteDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/delivery-notes/{id}")
	@Timed
	public ResponseEntity<Void> deleteDeliveryNote(@PathVariable Long id) {
		log.debug("REST request to delete DeliveryNote : {}", id);
		deliveryNoteService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * GET /delivery-notes : get all the deliveryNotes by reference.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         deliveryNotes in body
	 */
	@GetMapping("/delivery-notes/findByReference/{reference}")
	@Timed
	public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotes(@PathVariable String reference,
			Pageable pageable) {
		log.debug("REST request to get a page of DeliveryNotes");
		Page<DeliveryNoteDTO> page = deliveryNoteService.findAllByReference(reference, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /delivery-notes : get all the deliveryNotes by orderReference.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         deliveryNotes in body
	 */
	@GetMapping("/delivery-notes/findByOrderReference/{orderreference}")
	@Timed
	public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotesByOrderReference(
			@PathVariable String orderReference, Pageable pageable) {
		log.debug("REST request to get a page of DeliveryNotes");
		Page<DeliveryNoteDTO> page = deliveryNoteService.findAllByOrderReference(orderReference, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /delivery-notes : get all the deliveryNotes by delivered date.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         deliveryNotes in body
	 */
	@GetMapping("/delivery-notes/findByDeliveredDate/{deliveredDate}")
	@Timed
	public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotesByDeliveredDate(
			@PathVariable LocalDate deliveredDate, Pageable pageable) {
		log.debug("REST request to get a page of DeliveryNotes");
		Page<DeliveryNoteDTO> page = deliveryNoteService.findAllByDeliveredDate(deliveredDate, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /delivery-notes : get all the deliveryNotes by delivered date between
	 * two dates.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         deliveryNotes in body
	 */
	@GetMapping("/delivery-notes/findByDeliveredDateBetween/{startDate}/{endDate}")
	@Timed
	public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotesByDeliveredDateBetween(
			@PathVariable LocalDate startDate, @PathVariable LocalDate endDate, Pageable pageable) {
		log.debug("REST request to get a page of DeliveryNotes");
		Page<DeliveryNoteDTO> page = deliveryNoteService.findAllByDeliveredDateBetween(startDate, endDate, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /delivery-notes : get all the deliveryNotes by supplier id.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         deliveryNotes in body
	 */
	@GetMapping("/delivery-notes/findBySupplierId/{supplierId}")
	@Timed
	public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotesBySupplierId(@PathVariable Long supplierId,
			Pageable pageable) {
		log.debug("REST request to get a page of DeliveryNotes");
		Page<DeliveryNoteDTO> page = deliveryNoteService.findAllBySupplier_Id(supplierId, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	// --------------------------------------------------------------------------------------

	// Email

	// -------------------------------------------------------------------------------------
	/**
	 * This is a method which is used to send individual mail to the customer
	 * 
	 * @param to,subject,text
	 * 
	 */
	@PostMapping("/delivery-notes/sendMail/{to}/{subject}/{text}")
	@Timed
	public String sendMail(@PathVariable String to, @PathVariable String subject, @PathVariable String text) {

		return deliveryNoteService.sendMail(to, subject, text);
	}

	/**
	 * This is a method which is used to send individual mail to the supplier
	 * with attachment
	 * 
	 * @param to,subject,text
	 * 
	 */
	@PostMapping("/delivery-notes/sendMailWithAttachments")
	@Timed
	public String sendMailWithAttachments(@PathVariable String to, @PathVariable String subject,
			@PathVariable String text) throws MessagingException {

		return deliveryNoteService.sendMailWithAttachments(to, subject, text);
	}

	/**
	 * This is a method which is used to update inventory after creating
	 * delivery note.
	 * 
	 * 
	 */
	@PutMapping("/delivery-notes/update-inventory/{id}")
	@Timed
	public String updateInventory(@PathVariable long id) {
		deliveryNoteService.findOne(id);
		return null;
	}
	
	/**
	 * This is a method which is used to update inventory after creating
	 * delivery note.
	 * 
	 * 
	 */
	@PutMapping("/delivery-notes/reportGeneration{id}")
	@Timed
	public String reportGeneration(@PathVariable long id) {
		deliveryNoteService.findOne(id);
		return null;
	}
}

