package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.PurchaseLineService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.PurchaseLineDTO;
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
 * REST controller for managing PurchaseLine.
 */
@RestController
@RequestMapping("/api")
public class PurchaseLineResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseLineResource.class);

    private static final String ENTITY_NAME = "purchaseLine";

    private final PurchaseLineService purchaseLineService;

    public PurchaseLineResource(PurchaseLineService purchaseLineService) {
        this.purchaseLineService = purchaseLineService;
    }

    /**
     * POST  /purchase-lines : Create a new purchaseLine.
     *
     * @param purchaseLineDTO the purchaseLineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new purchaseLineDTO, or with status 400 (Bad Request) if the purchaseLine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/purchase-lines")
    @Timed
    public ResponseEntity<PurchaseLineDTO> createPurchaseLine(@RequestBody PurchaseLineDTO purchaseLineDTO) throws URISyntaxException {
        log.debug("REST request to save PurchaseLine : {}", purchaseLineDTO);
        if (purchaseLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseLineDTO result = purchaseLineService.save(purchaseLineDTO);
        return ResponseEntity.created(new URI("/api/purchase-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchase-lines : Updates an existing purchaseLine.
     *
     * @param purchaseLineDTO the purchaseLineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated purchaseLineDTO,
     * or with status 400 (Bad Request) if the purchaseLineDTO is not valid,
     * or with status 500 (Internal Server Error) if the purchaseLineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/purchase-lines")
    @Timed
    public ResponseEntity<PurchaseLineDTO> updatePurchaseLine(@RequestBody PurchaseLineDTO purchaseLineDTO) throws URISyntaxException {
        log.debug("REST request to update PurchaseLine : {}", purchaseLineDTO);
        if (purchaseLineDTO.getId() == null) {
            return createPurchaseLine(purchaseLineDTO);
        }
        PurchaseLineDTO result = purchaseLineService.save(purchaseLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /purchase-lines : get all the purchaseLines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of purchaseLines in body
     */
    @GetMapping("/purchase-lines")
    @Timed
    public ResponseEntity<List<PurchaseLineDTO>> getAllPurchaseLines(Pageable pageable) {
        log.debug("REST request to get a page of PurchaseLines");
        Page<PurchaseLineDTO> page = purchaseLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-lines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchase-lines/:id : get the "id" purchaseLine.
     *
     * @param id the id of the purchaseLineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the purchaseLineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/purchase-lines/{id}")
    @Timed
    public ResponseEntity<PurchaseLineDTO> getPurchaseLine(@PathVariable Long id) {
        log.debug("REST request to get PurchaseLine : {}", id);
        PurchaseLineDTO purchaseLineDTO = purchaseLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(purchaseLineDTO));
    }

    /**
     * DELETE  /purchase-lines/:id : delete the "id" purchaseLine.
     *
     * @param id the id of the purchaseLineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/purchase-lines/{id}")
    @Timed
    public ResponseEntity<Void> deletePurchaseLine(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseLine : {}", id);
        purchaseLineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/purchase-orders/findByproductReference/{productReference}")
    @Timed
    public ResponseEntity<List<PurchaseLineDTO>> findByProductReference(@PathVariable String productReference ,Pageable pageable) {
    	log.debug("REST request to get a page of PurchaseOrder :{}", productReference);
        Page<PurchaseLineDTO> page = purchaseLineService.findByPurchaseLine(productReference, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
}
