package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.PurchaseLine;
import com.diviso.purchase.domain.PurchaseOrder;
import com.diviso.purchase.service.dto.PurchaseLineDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-20T11:19:01+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class PurchaseLineMapperImpl implements PurchaseLineMapper {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public List<PurchaseLine> toEntity(List<PurchaseLineDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PurchaseLine> list = new ArrayList<PurchaseLine>( dtoList.size() );
        for ( PurchaseLineDTO purchaseLineDTO : dtoList ) {
            list.add( toEntity( purchaseLineDTO ) );
        }

        return list;
    }

    @Override
    public List<PurchaseLineDTO> toDto(List<PurchaseLine> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PurchaseLineDTO> list = new ArrayList<PurchaseLineDTO>( entityList.size() );
        for ( PurchaseLine purchaseLine : entityList ) {
            list.add( toDto( purchaseLine ) );
        }

        return list;
    }

    @Override
    public PurchaseLineDTO toDto(PurchaseLine purchaseLine) {
        if ( purchaseLine == null ) {
            return null;
        }

        PurchaseLineDTO purchaseLineDTO = new PurchaseLineDTO();

        Long id = purchaseLinePurchaseOrderId( purchaseLine );
        if ( id != null ) {
            purchaseLineDTO.setPurchaseOrderId( id );
        }
        purchaseLineDTO.setId( purchaseLine.getId() );
        purchaseLineDTO.setProductReference( purchaseLine.getProductReference() );
        purchaseLineDTO.setProductPrice( purchaseLine.getProductPrice() );
        purchaseLineDTO.setProductTax( purchaseLine.getProductTax() );
        purchaseLineDTO.setQuantity( purchaseLine.getQuantity() );

        return purchaseLineDTO;
    }

    @Override
    public PurchaseLine toEntity(PurchaseLineDTO purchaseLineDTO) {
        if ( purchaseLineDTO == null ) {
            return null;
        }

        PurchaseLine purchaseLine = new PurchaseLine();

        purchaseLine.setPurchaseOrder( purchaseOrderMapper.fromId( purchaseLineDTO.getPurchaseOrderId() ) );
        purchaseLine.setId( purchaseLineDTO.getId() );
        purchaseLine.setProductReference( purchaseLineDTO.getProductReference() );
        purchaseLine.setProductPrice( purchaseLineDTO.getProductPrice() );
        purchaseLine.setProductTax( purchaseLineDTO.getProductTax() );
        purchaseLine.setQuantity( purchaseLineDTO.getQuantity() );

        return purchaseLine;
    }

    private Long purchaseLinePurchaseOrderId(PurchaseLine purchaseLine) {
        if ( purchaseLine == null ) {
            return null;
        }
        PurchaseOrder purchaseOrder = purchaseLine.getPurchaseOrder();
        if ( purchaseOrder == null ) {
            return null;
        }
        Long id = purchaseOrder.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
