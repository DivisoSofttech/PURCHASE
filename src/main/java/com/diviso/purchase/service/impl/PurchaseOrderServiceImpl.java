package com.diviso.purchase.service.impl;

import com.diviso.purchase.service.PurchaseOrderService;


import com.diviso.purchase.domain.PurchaseLine;
import com.diviso.purchase.domain.PurchaseOrder;
import com.diviso.purchase.domain.Quotation;
import com.diviso.purchase.domain.QuotationLine;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.repository.PurchaseOrderRepository;
import com.diviso.purchase.service.dto.PurchaseOrderDTO;
import com.diviso.purchase.service.mapper.PurchaseOrderMapper;
import com.diviso.purchase.service.model.AddressModel;
import com.diviso.purchase.service.model.ContactModel;
import com.diviso.purchase.service.model.PurchaseLineModel;
import com.diviso.purchase.service.model.PurchaseOrderModel;
import com.diviso.purchase.service.model.StatussModel;
import com.diviso.purchase.service.model.SupplierModel;

import io.swagger.annotations.ApiParam;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PurchaseOrder.
 */
@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final PurchaseOrderMapper purchaseOrderMapper;
    
    @Autowired
	public JavaMailSender emailSender;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
    }

    /**
     * Save a purchaseOrder.
     *
     * @param purchaseOrderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO) {
        log.debug("Request to save PurchaseOrder : {}", purchaseOrderDTO);
        PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(purchaseOrderDTO);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrderMapper.toDto(purchaseOrder);
    }

    /**
     * Get all the purchaseOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PurchaseOrders");
        return purchaseOrderRepository.findAll(pageable)
            .map(purchaseOrderMapper::toDto);
    }

    /**
     * Get one purchaseOrder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDTO findOne(Long id) {
        log.debug("Request to get PurchaseOrder : {}", id);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);
        return purchaseOrderMapper.toDto(purchaseOrder);
    }

    /**
     * Delete the purchaseOrder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseOrder : {}", id);
        purchaseOrderRepository.delete(id);
    }
    
    /**
     * create purchaseorder by quotation.
     * @param quotation
     */

	@Override
	public void save(Quotation quotation) {
		log.debug("Request to save PurchaseOrder using Quotation : {}", quotation);
		
		LocalDate localDate = LocalDate.now();
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPurchaseDate(localDate);
		purchaseOrder.setSupplier(quotation.getSupplier());
		purchaseOrder.setStatuss(quotation.getStatuss());
		
		for(QuotationLine ql: quotation.getQuotationLines()) {
			
			PurchaseLine purchaseLine = new PurchaseLine();
			purchaseLine.setProductReference(ql.getReference());
			purchaseLine.setProductPrice(ql.getPrice());
			purchaseLine.setProductTax(ql.getTax());
			purchaseLine.setQuantity(ql.getAvailableQuantity());
			
			purchaseOrder.getPurchaseLines().add(purchaseLine);
		}
		purchaseOrderRepository.save(purchaseOrder);	
	}
     
     /**
      * Get purchase order by date.
      * @param purchaseDate the id of the entity
      */

 	@Override
 	@Transactional(readOnly = true)
 	public Page<PurchaseOrderDTO> findByPurchaseOrder(LocalDate purchaseDate, Pageable pageable) {
 		
 		log.debug("Request to delete PurchaseOrder : {}", purchaseDate);
 		return purchaseOrderRepository.findByPurchaseDate(purchaseDate,pageable)
 	            .map(purchaseOrderMapper::toDto);
 	}
 	
 	/**
     * Get purchase order by reference.
     * @param purchaseDate the id of the entity
     */

	@Override
	@Transactional(readOnly = true)
	public Page<PurchaseOrderDTO> findByPurchaseOrder(String reference, Pageable pageable) {
		
		log.debug("Request to delete PurchaseOrder : {}", reference);
		return purchaseOrderRepository.findByReference(reference,pageable)
	            .map(purchaseOrderMapper::toDto);
	}
	
	/**
     * This is a method for issue purchase order by communicating with report service
     * @param id the id of the entity
     */
	@Override
	public String issuePurchaseOrder(Long purchaseOrderId) throws JRException {
		
		
		///call sendMailWithAttachment method
		try {
			
			sendMailWithAttachment(purchaseOrderId);
		}
		catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * This is a method which is used to send individual mail to the customer with attachment
	 * @param id the id of the entity
	 */
	@Override
	public String sendMailWithAttachment(Long purchaseOrderId) throws MessagingException {
		
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(purchaseOrderId);
		
		MimeMessage message = emailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(""+purchaseOrder.getSupplier().getContact().getMailId());
		helper.setSubject("subject");
		helper.setText("text");

		FileSystemResource file = new FileSystemResource(new File("/home/vishnu/LXI/purchase_order.pdf"));
		helper.addAttachment("Invoice", file);
		
		emailSender.send(message);

		return "Mail Successfully sent..";
	}
	
	 /**
     * Get one purchaseOrderModel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderModel marshelledFindOne(Long id) {
        log.debug("Request to get PurchaseOrder : {}", id);
        
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);
        
        PurchaseOrderModel purchaseOrderModel = new PurchaseOrderModel();
        purchaseOrderModel.setId(purchaseOrder.getId());
        purchaseOrderModel.setReference(purchaseOrder.getReference());
        purchaseOrderModel.setPurchaseDate(purchaseOrder.getPurchaseDate());
        
        SupplierModel supplierModel = new SupplierModel();
        supplierModel.setId(purchaseOrder.getSupplier().getId());
        supplierModel.setReference(purchaseOrder.getSupplier().getReference());
        supplierModel.setFirstName(purchaseOrder.getSupplier().getFirstName());
        supplierModel.setLastName(purchaseOrder.getSupplier().getLastName());
        
        AddressModel permanentAddressModel = new AddressModel();
        permanentAddressModel.setId(purchaseOrder.getSupplier().getPermanentAddress().getId());
        permanentAddressModel.setPlace(purchaseOrder.getSupplier().getPermanentAddress().getPlace());
        permanentAddressModel.setDistrict(purchaseOrder.getSupplier().getPermanentAddress().getDistrict());
        permanentAddressModel.setState(purchaseOrder.getSupplier().getPermanentAddress().getState());
        permanentAddressModel.setPinCode(purchaseOrder.getSupplier().getPermanentAddress().getPinCode());
        
        //Set AddressModel to SupplierModel
        supplierModel.setPermanentAddressModel(permanentAddressModel);
        
        ContactModel contactModel = new ContactModel();
        contactModel.setId(purchaseOrder.getSupplier().getContact().getId());
        contactModel.setMailId(purchaseOrder.getSupplier().getContact().getMailId());
        contactModel.setPhoneNumber1(purchaseOrder.getSupplier().getContact().getPhoneNumber1());
        contactModel.setPhoneNumber2(purchaseOrder.getSupplier().getContact().getPhoneNumber2());
        contactModel.setCompanyName(purchaseOrder.getSupplier().getContact().getCompanyName());
        
        //Set ContactModel to  SupplierModel
        supplierModel.setContactModel(contactModel);
        
        //Set SupplierModel to PurchaseOrderModel
        purchaseOrderModel.setSupplierModel(supplierModel);
        
        StatussModel statussModel = new StatussModel();
        statussModel.setId(purchaseOrder.getStatuss().getId());
        statussModel.setName(purchaseOrder.getStatuss().getName());
        statussModel.setStatusLevel(purchaseOrder.getStatuss().getStatusLevel());
        
        //Set StatussModel to  PurchaseOrderModel
        purchaseOrderModel.setStatussModel(statussModel);
        
        //Set PurchaseLinesModel to PurchaseOrderModel
        for(PurchaseLine pl: purchaseOrder.getPurchaseLines()) {
        	
        	PurchaseLineModel purchaseLineModel = new PurchaseLineModel();
        	purchaseLineModel.setId(pl.getId());
        	purchaseLineModel.setProductReference(pl.getProductReference());
        	purchaseLineModel.setProductPrice(pl.getProductPrice());
        	purchaseLineModel.setProductTax(pl.getProductTax());
        	purchaseLineModel.setQuantity(pl.getQuantity());
        	
        	purchaseOrderModel.getPurchaseLinesModel().add(purchaseLineModel);
        }
        
        return purchaseOrderModel;
    }
}
