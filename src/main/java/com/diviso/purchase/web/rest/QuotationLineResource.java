package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.QuotationLineService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.QuotationLineDTO;
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
 * REST controller for managing QuotationLine.
 */
@RestController
@RequestMapping("/api")
public class QuotationLineResource {

    private final Logger log = LoggerFactory.getLogger(QuotationLineResource.class);

    private static final String ENTITY_NAME = "quotationLine";

    private final QuotationLineService quotationLineService;

    public QuotationLineResource(QuotationLineService quotationLineService) {
        this.quotationLineService = quotationLineService;
    }

    /**
     * POST  /quotation-lines : Create a new quotationLine.
     *
     * @param quotationLineDTO the quotationLineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationLineDTO, or with status 400 (Bad Request) if the quotationLine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotation-lines")
    @Timed
    public ResponseEntity<QuotationLineDTO> createQuotationLine(@RequestBody QuotationLineDTO quotationLineDTO) throws URISyntaxException {
        log.debug("REST request to save QuotationLine : {}", quotationLineDTO);
        if (quotationLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotationLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationLineDTO result = quotationLineService.save(quotationLineDTO);
        return ResponseEntity.created(new URI("/api/quotation-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotation-lines : Updates an existing quotationLine.
     *
     * @param quotationLineDTO the quotationLineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationLineDTO,
     * or with status 400 (Bad Request) if the quotationLineDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationLineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotation-lines")
    @Timed
    public ResponseEntity<QuotationLineDTO> updateQuotationLine(@RequestBody QuotationLineDTO quotationLineDTO) throws URISyntaxException {
        log.debug("REST request to update QuotationLine : {}", quotationLineDTO);
        if (quotationLineDTO.getId() == null) {
            return createQuotationLine(quotationLineDTO);
        }
        QuotationLineDTO result = quotationLineService.save(quotationLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotation-lines : get all the quotationLines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quotationLines in body
     */
    @GetMapping("/quotation-lines")
    @Timed
    public ResponseEntity<List<QuotationLineDTO>> getAllQuotationLines(Pageable pageable) {
        log.debug("REST request to get a page of QuotationLines");
        Page<QuotationLineDTO> page = quotationLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotation-lines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotation-lines/:id : get the "id" quotationLine.
     *
     * @param id the id of the quotationLineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationLineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotation-lines/{id}")
    @Timed
    public ResponseEntity<QuotationLineDTO> getQuotationLine(@PathVariable Long id) {
        log.debug("REST request to get QuotationLine : {}", id);
        QuotationLineDTO quotationLineDTO = quotationLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationLineDTO));
    }

    /**
     * DELETE  /quotation-lines/:id : delete the "id" quotationLine.
     *
     * @param id the id of the quotationLineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotation-lines/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotationLine(@PathVariable Long id) {
        log.debug("REST request to delete QuotationLine : {}", id);
        quotationLineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /*  *  *  *  *  *  *
     *   EXTRA METHODS
     *  *  *  *  *  *  */

    
}
