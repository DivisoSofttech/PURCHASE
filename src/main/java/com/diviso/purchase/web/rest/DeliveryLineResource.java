package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.DeliveryLineService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.DeliveryLineDTO;
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
 * REST controller for managing DeliveryLine.
 */
@RestController
@RequestMapping("/api")
public class DeliveryLineResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryLineResource.class);

    private static final String ENTITY_NAME = "deliveryLine";

    private final DeliveryLineService deliveryLineService;

    public DeliveryLineResource(DeliveryLineService deliveryLineService) {
        this.deliveryLineService = deliveryLineService;
    }

    /**
     * POST  /delivery-lines : Create a new deliveryLine.
     *
     * @param deliveryLineDTO the deliveryLineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveryLineDTO, or with status 400 (Bad Request) if the deliveryLine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delivery-lines")
    @Timed
    public ResponseEntity<DeliveryLineDTO> createDeliveryLine(@RequestBody DeliveryLineDTO deliveryLineDTO) throws URISyntaxException {
        log.debug("REST request to save DeliveryLine : {}", deliveryLineDTO);
        if (deliveryLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveryLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryLineDTO result = deliveryLineService.save(deliveryLineDTO);
        return ResponseEntity.created(new URI("/api/delivery-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-lines : Updates an existing deliveryLine.
     *
     * @param deliveryLineDTO the deliveryLineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryLineDTO,
     * or with status 400 (Bad Request) if the deliveryLineDTO is not valid,
     * or with status 500 (Internal Server Error) if the deliveryLineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-lines")
    @Timed
    public ResponseEntity<DeliveryLineDTO> updateDeliveryLine(@RequestBody DeliveryLineDTO deliveryLineDTO) throws URISyntaxException {
        log.debug("REST request to update DeliveryLine : {}", deliveryLineDTO);
        if (deliveryLineDTO.getId() == null) {
            return createDeliveryLine(deliveryLineDTO);
        }
        DeliveryLineDTO result = deliveryLineService.save(deliveryLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /delivery-lines : get all the deliveryLines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryLines in body
     */
    @GetMapping("/delivery-lines")
    @Timed
    public ResponseEntity<List<DeliveryLineDTO>> getAllDeliveryLines(Pageable pageable) {
        log.debug("REST request to get a page of DeliveryLines");
        Page<DeliveryLineDTO> page = deliveryLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-lines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /delivery-lines/:id : get the "id" deliveryLine.
     *
     * @param id the id of the deliveryLineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveryLineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/delivery-lines/{id}")
    @Timed
    public ResponseEntity<DeliveryLineDTO> getDeliveryLine(@PathVariable Long id) {
        log.debug("REST request to get DeliveryLine : {}", id);
        DeliveryLineDTO deliveryLineDTO = deliveryLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(deliveryLineDTO));
    }

    /**
     * DELETE  /delivery-lines/:id : delete the "id" deliveryLine.
     *
     * @param id the id of the deliveryLineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-lines/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveryLine(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryLine : {}", id);
        deliveryLineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
