package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.PurchaseOrder;
import com.diviso.purchase.domain.Statuss;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.service.dto.PurchaseOrderDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-20T13:59:35+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class PurchaseOrderMapperImpl implements PurchaseOrderMapper {

    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private StatussMapper statussMapper;

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

        Long id = purchaseOrderSupplierId( purchaseOrder );
        if ( id != null ) {
            purchaseOrderDTO.setSupplierId( id );
        }
        Long id1 = purchaseOrderStatussId( purchaseOrder );
        if ( id1 != null ) {
            purchaseOrderDTO.setStatussId( id1 );
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

        purchaseOrder.setStatuss( statussMapper.fromId( purchaseOrderDTO.getStatussId() ) );
        purchaseOrder.setSupplier( supplierMapper.fromId( purchaseOrderDTO.getSupplierId() ) );
        purchaseOrder.setId( purchaseOrderDTO.getId() );
        purchaseOrder.setReference( purchaseOrderDTO.getReference() );
        purchaseOrder.setPurchaseDate( purchaseOrderDTO.getPurchaseDate() );

        return purchaseOrder;
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

    private Long purchaseOrderStatussId(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        Statuss statuss = purchaseOrder.getStatuss();
        if ( statuss == null ) {
            return null;
        }
        Long id = statuss.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
