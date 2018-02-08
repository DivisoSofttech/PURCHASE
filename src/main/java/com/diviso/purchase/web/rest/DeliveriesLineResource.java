package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.DeliveriesLineService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.DeliveriesLineDTO;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DeliveriesLine.
 */
@RestController
@RequestMapping("/api")
public class DeliveriesLineResource {

    private final Logger log = LoggerFactory.getLogger(DeliveriesLineResource.class);

    private static final String ENTITY_NAME = "deliveriesLine";

    private final DeliveriesLineService deliveriesLineService;

    public DeliveriesLineResource(DeliveriesLineService deliveriesLineService) {
        this.deliveriesLineService = deliveriesLineService;
    }

    /**
     * POST  /deliveries-lines : Create a new deliveriesLine.
     *
     * @param deliveriesLineDTO the deliveriesLineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveriesLineDTO, or with status 400 (Bad Request) if the deliveriesLine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/deliveries-lines")
    @Timed
    public ResponseEntity<DeliveriesLineDTO> createDeliveriesLine(@RequestBody DeliveriesLineDTO deliveriesLineDTO) throws URISyntaxException {
        log.debug("REST request to save DeliveriesLine : {}", deliveriesLineDTO);
        if (deliveriesLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveriesLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveriesLineDTO result = deliveriesLineService.save(deliveriesLineDTO);
        return ResponseEntity.created(new URI("/api/deliveries-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /deliveries-lines : Updates an existing deliveriesLine.
     *
     * @param deliveriesLineDTO the deliveriesLineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveriesLineDTO,
     * or with status 400 (Bad Request) if the deliveriesLineDTO is not valid,
     * or with status 500 (Internal Server Error) if the deliveriesLineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/deliveries-lines")
    @Timed
    public ResponseEntity<DeliveriesLineDTO> updateDeliveriesLine(@RequestBody DeliveriesLineDTO deliveriesLineDTO) throws URISyntaxException {
        log.debug("REST request to update DeliveriesLine : {}", deliveriesLineDTO);
        if (deliveriesLineDTO.getId() == null) {
            return createDeliveriesLine(deliveriesLineDTO);
        }
        DeliveriesLineDTO result = deliveriesLineService.save(deliveriesLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveriesLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /deliveries-lines : get all the deliveriesLines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveriesLines in body
     */
    @GetMapping("/deliveries-lines")
    @Timed
    public ResponseEntity<List<DeliveriesLineDTO>> getAllDeliveriesLines(Pageable pageable) {
        log.debug("REST request to get a page of DeliveriesLines");
        Page<DeliveriesLineDTO> page = deliveriesLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/deliveries-lines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /deliveries-lines/:id : get the "id" deliveriesLine.
     *
     * @param id the id of the deliveriesLineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveriesLineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/deliveries-lines/{id}")
    @Timed
    public ResponseEntity<DeliveriesLineDTO> getDeliveriesLine(@PathVariable Long id) {
        log.debug("REST request to get DeliveriesLine : {}", id);
        DeliveriesLineDTO deliveriesLineDTO = deliveriesLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(deliveriesLineDTO));
    }

    /**
     * DELETE  /deliveries-lines/:id : delete the "id" deliveriesLine.
     *
     * @param id the id of the deliveriesLineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deliveries-lines/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveriesLine(@PathVariable Long id) {
        log.debug("REST request to delete DeliveriesLine : {}", id);
        deliveriesLineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
