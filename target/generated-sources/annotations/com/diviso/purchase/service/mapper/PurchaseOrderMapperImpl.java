package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.PurchaseOrder;
import com.diviso.purchase.domain.Status;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.service.dto.PurchaseOrderDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-02T16:10:42+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class PurchaseOrderMapperImpl implements PurchaseOrderMapper {

    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private StatusMapper statusMapper;

    @Override
    public List<PurchaseOrder> toEntity(List<PurchaseOrderDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>( dtoList.size() );
        for ( PurchaseOrderDTO purchaseOrderDTO : dtoList ) {
            list.add( toEntity( purchaseOrderDTO ) );
        }

        return list;
    }

    @Override
    public List<PurchaseOrderDTO> toDto(List<PurchaseOrder> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PurchaseOrderDTO> list = new ArrayList<PurchaseOrderDTO>( entityList.size() );
        for ( PurchaseOrder purchaseOrder : entityList ) {
            list.add( toDto( purchaseOrder ) );
        }

        return list;
    }

    @Override
    public PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();

        Long id = purchaseOrderStatusId( purchaseOrder );
        if ( id != null ) {
            purchaseOrderDTO.setStatusId( id );
        }
        Long id1 = purchaseOrderSupplierId( purchaseOrder );
        if ( id1 != null ) {
            purchaseOrderDTO.setSupplierId( id1 );
        }
        purchaseOrderDTO.setId( purchaseOrder.getId() );
        purchaseOrderDTO.setReference( purchaseOrder.getReference() );
        purchaseOrderDTO.setPurchaseDate( purchaseOrder.getPurchaseDate() );

        return purchaseOrderDTO;
    }

    @Override
    public PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO) {
        if ( purchaseOrderDTO == null ) {
            return null;
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setSupplier( supplierMapper.fromId( purchaseOrderDTO.getSupplierId() ) );
        purchaseOrder.setStatus( statusMapper.fromId( purchaseOrderDTO.getStatusId() ) );
        purchaseOrder.setId( purchaseOrderDTO.getId() );
        purchaseOrder.setReference( purchaseOrderDTO.getReference() );
        purchaseOrder.setPurchaseDate( purchaseOrderDTO.getPurchaseDate() );

        return purchaseOrder;
    }

    private Long purchaseOrderStatusId(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        Status status = purchaseOrder.getStatus();
        if ( status == null ) {
            return null;
        }
        Long id = status.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long purchaseOrderSupplierId(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        Supplier supplier = purchaseOrder.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        Long id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
