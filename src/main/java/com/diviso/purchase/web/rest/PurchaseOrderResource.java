package com.diviso.purchase.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.purchase.service.PurchaseOrderService;
import com.diviso.purchase.web.rest.errors.BadRequestAlertException;
import com.diviso.purchase.web.rest.util.HeaderUtil;
import com.diviso.purchase.web.rest.util.PaginationUtil;
import com.diviso.purchase.service.dto.PurchaseOrderDTO;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PurchaseOrder.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);

    private static final String ENTITY_NAME = "purchaseOrder";

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderResource(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    /**
     * POST  /purchase-orders : Create a new purchaseOrder.
     *
     * @param purchaseOrderDTO the purchaseOrderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new purchaseOrderDTO, or with status 400 (Bad Request) if the purchaseOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/purchase-orders")
    @Timed
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrder : {}", purchaseOrderDTO);
        if (purchaseOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderDTO result = purchaseOrderService.save(purchaseOrderDTO);
        return ResponseEntity.created(new URI("/api/purchase-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /purchase-orders : Updates an existing purchaseOrder.
     *
     * @param purchaseOrderDTO the purchaseOrderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated purchaseOrderDTO,
     * or with status 400 (Bad Request) if the purchaseOrderDTO is not valid,
     * or with status 500 (Internal Server Error) if the purchaseOrderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/purchase-orders")
    @Timed
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder : {}", purchaseOrderDTO);
        if (purchaseOrderDTO.getId() == null) {
            return createPurchaseOrder(purchaseOrderDTO);
        }
        PurchaseOrderDTO result = purchaseOrderService.save(purchaseOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, purchaseOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /purchase-orders : get all the purchaseOrders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of purchaseOrders in body
     */
    @GetMapping("/purchase-orders")
    @Timed
    public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders(Pageable pageable) {
        log.debug("REST request to get a page of PurchaseOrders");
        Page<PurchaseOrderDTO> page = purchaseOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /purchase-orders/:id : get the "id" purchaseOrder.
     *
     * @param id the id of the purchaseOrderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the purchaseOrderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/purchase-orders/{id}")
    @Timed
    public ResponseEntity<PurchaseOrderDTO> getPurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrder : {}", id);
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(purchaseOrderDTO));
    }
    
    

    /**
     * DELETE  /purchase-orders/:id : delete the "id" purchaseOrder.
     *
     * @param id the id of the purchaseOrderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/purchase-orders/{id}")
    @Timed
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrder : {}", id);
        purchaseOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /*-----------------------------------------------------------------------------------------------------------------------
     *EXTRA METHODS 
     * ----------------------------------------------------------------------------------------------------------------------
     */
    /**
     * GET  /purchase-orders/:purchase date : get the "purchase order" purchaseDate.
     * @param purchaseDate
     * @param pageable
     * @return
     */
    @GetMapping("/purchase-orders/findByPurchaseDate/{purchaseDate}")
    @Timed
    public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrder(@PathVariable LocalDate purchaseDate ,Pageable pageable) {
    	log.debug("REST request to get a page of PurchaseOrder :{}", purchaseDate);
        Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrder(purchaseDate , pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
   /**
    * GET  /purchase-orders/:purchase reference : get the "purchase order" reference.
    * @param reference
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findByReference/{reference}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrder(@PathVariable String reference ,Pageable pageable) {
   	log.debug("REST request to get a page of PurchaseOrder :{}", reference);
       Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrder(reference , pageable);
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
   
   //find by
   /**
    * GET  /purchase-orders/:supplier id : get the "supplier id" purchase order.
    * @param id
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findBySupplierId/{supplierId}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrder(@PathVariable  Long id ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", id);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrderSupplier(id , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
   /**
    * GET  /purchase-orders/:supplier reference : get the "supplier reference" purchase order.
    * @param reference
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findBySupplierReference/{supplierReference}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderSupplier(@PathVariable  String reference ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", reference);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrder(reference , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	   
   }
   /**
    * GET  /purchase-orders/:supplier date : get the "betweenDate" purchase order.
    * @param date
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findByDateBetWeen/{betWeenDate}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderSupplier(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", startDate,endDate);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseDateBetween(startDate,endDate , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
}
   /**
    * GET  /purchase-orders/:status id : get the "status id" purchase order.
    * @param status id
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findByStatusId/{statusId}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderStatuss(@PathVariable  Long id ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", id);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrderStatuss(id , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
   /**
    * GET  /purchase-orders/:supplier name: get the "supplier name" purchase order.
    * @param name
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findByStatussName/{statussName}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderStatuss(@PathVariable  String name ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", name);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrder(name , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	   
   }
   /**
    * GET  /purchase-orders/:purchaseLine id : get the "purchaseLine id" purchase order.
    * @param purchaseLine id
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findByPurchaseLineId/{purchaseLineId}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderPurchaseLine(@PathVariable  Long id ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", id);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrderStatuss(id , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
   /**
    * GET  /purchase-orders/:purchaseLine productReference : get the "purchaseLine productReference" purchase order.
    * @param purchaseLine productReference
    * @param pageable
    * @return
    */
   @GetMapping("/purchase-orders/findByPurchaseLineProductReference/{purchaseLineProductreference}")
   @Timed
   public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderPurchaseLine(@PathVariable  String productReference ,Pageable pageable) {
	   log.debug("REST request to get a page of PurchaseOrder :{}", productReference);
	   Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseOrderStatuss(productReference , pageable);
	   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
	   return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
// after and before purchaseDate
	/**
	 * GET /purchase-orders/:purchaseOrder puchaseDateAfter : get the "purchaseOrder
	 * purchaseDate" purchase order.
	 * 
	 * @param purchaseOrder
	 *            purchaseDateAfter
	 * @param pageable
	 * @return
	 */
	@GetMapping("/purchase-orders/findByDateAfterPurchaseOrder/{purchaseOrderPurchaseDate}")
	@Timed
	public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderPurchaseDateAfter(@PathVariable LocalDate dateAfter,
			Pageable pageable) {
		log.debug("REST request to get a page of PurchaseOrder :{}", dateAfter);
		Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseDateAfterPurchaseOrder(dateAfter, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);

	}
	
	/**
	 * GET /purchase-orders/:purchaseOrder puchaseDateBefore : get the "purchaseOrder
	 * purchaseDate" purchase order.
	 * 
	 * @param purchaseOrder
	 *            purchaseDateBefore
	 * @param pageable
	 * @return
	 */
	@GetMapping("/purchase-orders/findByDateBeforePurchaseOrder/{purchaseOrderPurchaseDate}")
	@Timed
	public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrderPurchaseDateBefore(@PathVariable LocalDate dateBefore,
			Pageable pageable) {
		log.debug("REST request to get a page of PurchaseOrder :{}", dateBefore);
		Page<PurchaseOrderDTO> page = purchaseOrderService.findByPurchaseDateAfterPurchaseOrder(dateBefore, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/purchase-orders");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	
}
}
