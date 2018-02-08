package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.StatussService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.StatussDTO;
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
 * REST controller for managing Statuss.
 */
@RestController
@RequestMapping("/api")
public class StatussResource {

    private final Logger log = LoggerFactory.getLogger(StatussResource.class);

    private static final String ENTITY_NAME = "statuss";

    private final StatussService statussService;

    public StatussResource(StatussService statussService) {
        this.statussService = statussService;
    }

    /**
     * POST  /statusses : Create a new statuss.
     *
     * @param statussDTO the statussDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statussDTO, or with status 400 (Bad Request) if the statuss has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/statusses")
    @Timed
    public ResponseEntity<StatussDTO> createStatuss(@RequestBody StatussDTO statussDTO) throws URISyntaxException {
        log.debug("REST request to save Statuss : {}", statussDTO);
        if (statussDTO.getId() != null) {
            throw new BadRequestAlertException("A new statuss cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatussDTO result = statussService.save(statussDTO);
        return ResponseEntity.created(new URI("/api/statusses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /statusses : Updates an existing statuss.
     *
     * @param statussDTO the statussDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statussDTO,
     * or with status 400 (Bad Request) if the statussDTO is not valid,
     * or with status 500 (Internal Server Error) if the statussDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/statusses")
    @Timed
    public ResponseEntity<StatussDTO> updateStatuss(@RequestBody StatussDTO statussDTO) throws URISyntaxException {
        log.debug("REST request to update Statuss : {}", statussDTO);
        if (statussDTO.getId() == null) {
            return createStatuss(statussDTO);
        }
        StatussDTO result = statussService.save(statussDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statussDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /statusses : get all the statusses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of statusses in body
     */
    @GetMapping("/statusses")
    @Timed
    public ResponseEntity<List<StatussDTO>> getAllStatusses(Pageable pageable) {
        log.debug("REST request to get a page of Statusses");
        Page<StatussDTO> page = statussService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/statusses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /statusses/:id : get the "id" statuss.
     *
     * @param id the id of the statussDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statussDTO, or with status 404 (Not Found)
     */
    @GetMapping("/statusses/{id}")
    @Timed
    public ResponseEntity<StatussDTO> getStatuss(@PathVariable Long id) {
        log.debug("REST request to get Statuss : {}", id);
        StatussDTO statussDTO = statussService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(statussDTO));
    }

    /**
     * DELETE  /statusses/:id : delete the "id" statuss.
     *
     * @param id the id of the statussDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/statusses/{id}")
    @Timed
    public ResponseEntity<Void> deleteStatuss(@PathVariable Long id) {
        log.debug("REST request to delete Statuss : {}", id);
        statussService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
