package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.QuotationService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.QuotationDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.event.MouseMotionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Date;
import java.util.Optional;

/**
 * REST controller for managing Quotation.
 */
@RestController
@RequestMapping("/api")
public class QuotationResource {

    private final Logger log = LoggerFactory.getLogger(QuotationResource.class);

    private static final String ENTITY_NAME = "quotation";

    private final QuotationService quotationService;

    public QuotationResource(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    /**
     * POST  /quotations : Create a new quotation.
     *
     * @param quotationDTO the quotationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationDTO, or with status 400 (Bad Request) if the quotation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotations")
    @Timed
    public ResponseEntity<QuotationDTO> createQuotation(@RequestBody QuotationDTO quotationDTO) throws URISyntaxException {
        log.debug("REST request to save Quotation : {}", quotationDTO);
        if (quotationDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationDTO result = quotationService.save(quotationDTO);
        return ResponseEntity.created(new URI("/api/quotations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotations : Updates an existing quotation.
     *
     * @param quotationDTO the quotationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationDTO,
     * or with status 400 (Bad Request) if the quotationDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotations")
    @Timed
    public ResponseEntity<QuotationDTO> updateQuotation(@RequestBody QuotationDTO quotationDTO) throws URISyntaxException {
        log.debug("REST request to update Quotation : {}", quotationDTO);
        if (quotationDTO.getId() == null) {
            return createQuotation(quotationDTO);
        }
        QuotationDTO result = quotationService.save(quotationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotations : get all the quotations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quotations in body
     */
    @GetMapping("/quotations")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getAllQuotations(Pageable pageable) {
        log.debug("REST request to get a page of Quotations");
        Page<QuotationDTO> page = quotationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /quotations/:id : get the "id" quotation.
     *
     * @param id the id of the quotationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotations/{id}")
    @Timed
    public ResponseEntity<QuotationDTO> getQuotation(@PathVariable Long id) {
        log.debug("REST request to get Quotation : {}", id);
        QuotationDTO quotationDTO = quotationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotationDTO));
    }

    /**
     * DELETE  /quotations/:id : delete the "id" quotation.
     *
     * @param id the id of the quotationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotations/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotation(@PathVariable Long id) {
        log.debug("REST request to delete Quotation : {}", id);
        quotationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
    
    /* * * * * * * * * * * *
     *    EXTRA METHODS
     * * * * * * * * * * * */


    @GetMapping("/quotations/findByReference/{reference}")
	@Timed
	public ResponseEntity<List<QuotationDTO>> getQuotationsByReference(@PathVariable String reference, Pageable pageable) {
		log.debug("REST request to get a page of Quotation by reference:{}", reference);
		Page<QuotationDTO> page = quotationService.findByReference(reference, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
    
    
    @GetMapping("/quotations/findBySupplierId/{id}")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getQuotationBySupplierId(@PathVariable  Long id ,Pageable pageable) {
 	   log.debug("REST request to get a page of Quotation :{}", id);
 	   Page<QuotationDTO> page = quotationService.findBySupplierId(id , pageable);
 	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
 	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/quotations/findBySupplierReference/{reference}")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getSupplierReference(@PathVariable  String reference ,Pageable pageable) {
 	   log.debug("REST request to get a page of Quotation :{}", reference);
 	   Page<QuotationDTO> page = quotationService.findBySupplierReference(reference , pageable);
 	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
 	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);  
    }
    
    @GetMapping("/quotations/findByStatussId/{id}")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getStatussId(@PathVariable  Long id ,Pageable pageable) {
 	   log.debug("REST request to get a page of Quotation :{}", id);
 	   Page<QuotationDTO> page = quotationService.findByStatussId(id , pageable);
 	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
 	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/quotations/findByStatussName/{name}")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getStatussName(@PathVariable  String name ,Pageable pageable) {
 	   log.debug("REST request to get a page of Quotation :{}", name);
 	   Page<QuotationDTO> page = quotationService.findByStatussName(name , pageable);
 	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
 	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/quotations/findByIssuedDate")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getQuotationsByIssuedDate(String date,Pageable pageable) throws ParseException {
        log.info("REST request to get a page of Quotations by Given date : " + date);		
        LocalDate localDate = new SimpleDateFormat("dd/MM/yyyy").parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Page<QuotationDTO> page = quotationService.findByIssuedDate(localDate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    

    @GetMapping("/quotations/findByIssuedDateBetween")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getQuotationsByIssuedDateBetween(String startDate,String endDate,Pageable pageable) throws ParseException {
        log.debug("REST request to get a page of Quotations between start and end date " + startDate,endDate);
        LocalDate start =  new SimpleDateFormat("dd/MM/yyyy").parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = new SimpleDateFormat("dd/MM/yyyy").parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Page<QuotationDTO> page = quotationService.findByIssuedDateBetween(start,end,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/quotations/findByIssuedDateAfter")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getQuotationsByIssuedDateAfter(String date,Pageable pageable) throws ParseException {
        log.info("REST request to get a page of Quotations After Given date : " + date);
        LocalDate localDate = new SimpleDateFormat("dd/MM/yyyy").parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Page<QuotationDTO> page = quotationService.findByIssuedDateAfter(localDate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/quotations/findByIssuedDateBefore")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getQuotationsByIssuedDateBefore(String date,Pageable pageable) throws ParseException {
        log.info("REST request to get a page of Quotations Before Given date : " + date);
        LocalDate localDate = new SimpleDateFormat("dd/MM/yyyy").parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Page<QuotationDTO> page = quotationService.findByIssuedDateBefore(localDate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/quotations/findByQuotationLineCount")
    @Timed
    public ResponseEntity<List<QuotationDTO>> getQuotationsByQuotationLineCount(int count,Pageable pageable) throws ParseException {
        log.info("REST request to get a page of Quotations by Quotation Line Count : " + count);
        Page<QuotationDTO> page = quotationService.findByQuotationLineCount(count,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
}