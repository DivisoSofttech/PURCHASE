package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.RatingTypeService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.RatingTypeDTO;
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
 * REST controller for managing RatingType.
 */
@RestController
@RequestMapping("/api")
public class RatingTypeResource {

    private final Logger log = LoggerFactory.getLogger(RatingTypeResource.class);

    private static final String ENTITY_NAME = "ratingType";

    private final RatingTypeService ratingTypeService;

    public RatingTypeResource(RatingTypeService ratingTypeService) {
        this.ratingTypeService = ratingTypeService;
    }

    /**
     * POST  /rating-types : Create a new ratingType.
     *
     * @param ratingTypeDTO the ratingTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ratingTypeDTO, or with status 400 (Bad Request) if the ratingType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rating-types")
    @Timed
    public ResponseEntity<RatingTypeDTO> createRatingType(@RequestBody RatingTypeDTO ratingTypeDTO) throws URISyntaxException {
        log.debug("REST request to save RatingType : {}", ratingTypeDTO);
        if (ratingTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new ratingType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RatingTypeDTO result = ratingTypeService.save(ratingTypeDTO);
        return ResponseEntity.created(new URI("/api/rating-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rating-types : Updates an existing ratingType.
     *
     * @param ratingTypeDTO the ratingTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ratingTypeDTO,
     * or with status 400 (Bad Request) if the ratingTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the ratingTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rating-types")
    @Timed
    public ResponseEntity<RatingTypeDTO> updateRatingType(@RequestBody RatingTypeDTO ratingTypeDTO) throws URISyntaxException {
        log.debug("REST request to update RatingType : {}", ratingTypeDTO);
        if (ratingTypeDTO.getId() == null) {
            return createRatingType(ratingTypeDTO);
        }
        RatingTypeDTO result = ratingTypeService.save(ratingTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ratingTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rating-types : get all the ratingTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ratingTypes in body
     */
    @GetMapping("/rating-types")
    @Timed
    public ResponseEntity<List<RatingTypeDTO>> getAllRatingTypes(Pageable pageable) {
        log.debug("REST request to get a page of RatingTypes");
        Page<RatingTypeDTO> page = ratingTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rating-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rating-types/:id : get the "id" ratingType.
     *
     * @param id the id of the ratingTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ratingTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rating-types/{id}")
    @Timed
    public ResponseEntity<RatingTypeDTO> getRatingType(@PathVariable Long id) {
        log.debug("REST request to get RatingType : {}", id);
        RatingTypeDTO ratingTypeDTO = ratingTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ratingTypeDTO));
    }

    /**
     * DELETE  /rating-types/:id : delete the "id" ratingType.
     *
     * @param id the id of the ratingTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rating-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteRatingType(@PathVariable Long id) {
        log.debug("REST request to delete RatingType : {}", id);
        ratingTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /rating-types : get all the ratingTypes by reference.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ratingTypes in body
     */
    @GetMapping("/rating-types/findByReference/{reference}")
    @Timed
    public ResponseEntity<List<RatingTypeDTO>> getAllRatingTypesByReference(@PathVariable String reference,Pageable pageable) {
        log.debug("REST request to get a page of RatingTypes");
        Page<RatingTypeDTO> page = ratingTypeService.findAllByReference(reference,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rating-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /rating-types : get all the ratingTypes by reference.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ratingTypes in body
     */
    @GetMapping("/rating-types/findByRatingType/{type}")
    @Timed
    public ResponseEntity<List<RatingTypeDTO>> getAllRatingTypesByType(@PathVariable String type,Pageable pageable) {
        log.debug("REST request to get a page of RatingTypes");
        Page<RatingTypeDTO> page = ratingTypeService.findAllByRating(type,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rating-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
