package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.DeliveryNoteService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DeliveryNote.
 */
@RestController
@RequestMapping("/api")
public class DeliveryNoteResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryNoteResource.class);

    private static final String ENTITY_NAME = "deliveryNote";

    private final DeliveryNoteService deliveryNoteService;

    public DeliveryNoteResource(DeliveryNoteService deliveryNoteService) {
        this.deliveryNoteService = deliveryNoteService;
    }

    /**
     * POST  /delivery-notes : Create a new deliveryNote.
     *
     * @param deliveryNoteDTO the deliveryNoteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveryNoteDTO, or with status 400 (Bad Request) if the deliveryNote has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delivery-notes")
    @Timed
    public ResponseEntity<DeliveryNoteDTO> createDeliveryNote(@RequestBody DeliveryNoteDTO deliveryNoteDTO) throws URISyntaxException {
        log.debug("REST request to save DeliveryNote : {}", deliveryNoteDTO);
        if (deliveryNoteDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveryNote cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryNoteDTO result = deliveryNoteService.save(deliveryNoteDTO);
        return ResponseEntity.created(new URI("/api/delivery-notes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-notes : Updates an existing deliveryNote.
     *
     * @param deliveryNoteDTO the deliveryNoteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryNoteDTO,
     * or with status 400 (Bad Request) if the deliveryNoteDTO is not valid,
     * or with status 500 (Internal Server Error) if the deliveryNoteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-notes")
    @Timed
    public ResponseEntity<DeliveryNoteDTO> updateDeliveryNote(@RequestBody DeliveryNoteDTO deliveryNoteDTO) throws URISyntaxException {
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
     * GET  /delivery-notes : get all the deliveryNotes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryNotes in body
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
     * GET  /delivery-notes/:id : get the "id" deliveryNote.
     *
     * @param id the id of the deliveryNoteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveryNoteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/delivery-notes/{id}")
    @Timed
    public ResponseEntity<DeliveryNoteDTO> getDeliveryNote(@PathVariable Long id) {
        log.debug("REST request to get DeliveryNote : {}", id);
        DeliveryNoteDTO deliveryNoteDTO = deliveryNoteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(deliveryNoteDTO));
    }

    
    /**
     * GET  /delivery-notes : get all the deliveryNotes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryNotes in body
     */
    @GetMapping("/delivery-notes/findByPurchaseDate/{purchaseDate}")
    @Timed
    public ResponseEntity<List<DeliveryNoteDTO>> getAllDeliveryNotes(@PathVariable LocalDate purchaseDate,Pageable pageable) {
        log.debug("REST request to get a page of DeliveryNotes:{}",purchaseDate);
        Page<DeliveryNoteDTO> page = deliveryNoteService.findByPurchaseDate(purchaseDate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-notes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    
    
    /**
     * DELETE  /delivery-notes/:id : delete the "id" deliveryNote.
     *
     * @param id the id of the deliveryNoteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-notes/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveryNote(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryNote : {}", id);
        deliveryNoteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
