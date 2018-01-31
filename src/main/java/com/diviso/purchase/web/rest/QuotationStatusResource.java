package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.QuotationStatusService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.QuotationStatusDTO;
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
 * REST controller for managing QuotationStatus.
 */
@RestController
@RequestMapping("/api")
public class QuotationStatusResource {

    private final Logger log = LoggerFactory.getLogger(QuotationStatusResource.class);

    private static final String ENTITY_NAME = "quotationStatus";

    private final QuotationStatusService quotationStatusService;

    public QuotationStatusResource(QuotationStatusService quotationStatusService) {
        this.quotationStatusService = quotationStatusService;
    }

    /**
     * POST  /quotation-statuses : Create a new quotationStatus.
     *
     * @param quotationStatusDTO the quotationStatusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationStatusDTO, or with status 400 (Bad Request) if the quotationStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-statuses")
    @Timed
    public ResponseEntity<QuotationStatusDTO> createQuotationStatus(@RequestBody QuotationStatusDTO quotationStatusDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationStatus : {}", quotationStatusDTO);
        if (quotationStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationStatusDTO result = quotationStatusService.save(quotationStatusDTO);
        return ResponseEntity.created(new URI("/api/quotation-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-statuses : Updates an existing quotationStatus.
     *
     * @param quotationStatusDTO the quotationStatusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationStatusDTO,
     * or with status 400 (Bad Request) if the quotationStatusDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationStatusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-statuses")
    @Timed
    public ResponseEntity<QuotationStatusDTO> updateQuotationStatus(@RequestBody QuotationStatusDTO quotationStatusDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationStatus : {}", quotationStatusDTO);
        if (quotationStatusDTO.getId() == null) {
            return createQuotationStatus(quotationStatusDTO);
        }
        QuotationStatusDTO result = quotationStatusService.save(quotationStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-statuses : get all the quotationStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quotationStatuses in body
     */
    @GetMapping("/quotation-statuses")
    @Timed
    public ResponseEntity<List<QuotationStatusDTO>> getAllQuotationStatuses(Pageable pageable) {
        log.debug("REST request to get a page of QuotationStatuses");
        Page<QuotationStatusDTO> page = quotationStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-statuses/:id : get the "id" quotationStatus.
     *
     * @param id the id of the quotationStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-statuses/{id}")
    @Timed
    public ResponseEntity<QuotationStatusDTO> getQuotationStatus(@PathVariable Long id) {
        log.debug("REST request to get QuotationStatus : {}", id);
        QuotationStatusDTO quotationStatusDTO = quotationStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationStatusDTO));
    }

    /**
     * DELETE  /quotation-statuses/:id : delete the "id" quotationStatus.
     *
     * @param id the id of the quotationStatusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationStatus(@PathVariable Long id) {
        log.debug("REST request to delete QuotationStatus : {}", id);
        quotationStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
