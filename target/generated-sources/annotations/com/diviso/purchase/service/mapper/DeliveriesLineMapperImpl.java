package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.DeliveriesLine;
import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.service.dto.DeliveriesLineDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-17T13:52:43+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class DeliveriesLineMapperImpl implements DeliveriesLineMapper {

    @Autowired
    private DeliveryNoteMapper deliveryNoteMapper;

    @Override
    public List<DeliveriesLine> toEntity(List<DeliveriesLineDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DeliveriesLine> list = new ArrayList<DeliveriesLine>( dtoList.size() );
        for ( DeliveriesLineDTO deliveriesLineDTO : dtoList ) {
            list.add( toEntity( deliveriesLineDTO ) );
        }

        return list;
    }

    @Override
    public List<DeliveriesLineDTO> toDto(List<DeliveriesLine> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DeliveriesLineDTO> list = new ArrayList<DeliveriesLineDTO>( entityList.size() );
        for ( DeliveriesLine deliveriesLine : entityList ) {
            list.add( toDto( deliveriesLine ) );
        }

        return list;
    }

    @Override
    public DeliveriesLineDTO toDto(DeliveriesLine deliveriesLine) {
        if ( deliveriesLine == null ) {
            return null;
        }

        DeliveriesLineDTO deliveriesLineDTO = new DeliveriesLineDTO();

        Long id = deliveriesLineDeliveryNoteId( deliveriesLine );
        if ( id != null ) {
            deliveriesLineDTO.setDeliveryNoteId( id );
        }
        deliveriesLineDTO.setId( deliveriesLine.getId() );
        deliveriesLineDTO.setReference( deliveriesLine.getReference() );
        deliveriesLineDTO.setPrice( deliveriesLine.getPrice() );
        deliveriesLineDTO.setTax( deliveriesLine.getTax() );
        deliveriesLineDTO.setQuantity( deliveriesLine.getQuantity() );

        return deliveriesLineDTO;
    }

    @Override
    public DeliveriesLine toEntity(DeliveriesLineDTO deliveriesLineDTO) {
        if ( deliveriesLineDTO == null ) {
            return null;
        }

        DeliveriesLine deliveriesLine = new DeliveriesLine();

        deliveriesLine.setDeliveryNote( deliveryNoteMapper.fromId( deliveriesLineDTO.getDeliveryNoteId() ) );
        deliveriesLine.setId( deliveriesLineDTO.getId() );
        deliveriesLine.setReference( deliveriesLineDTO.getReference() );
        deliveriesLine.setPrice( deliveriesLineDTO.getPrice() );
        deliveriesLine.setTax( deliveriesLineDTO.getTax() );
        deliveriesLine.setQuantity( deliveriesLineDTO.getQuantity() );

        return deliveriesLine;
    }

    private Long deliveriesLineDeliveryNoteId(DeliveriesLine deliveriesLine) {
        if ( deliveriesLine == null ) {
            return null;
        }
        DeliveryNote deliveryNote = deliveriesLine.getDeliveryNote();
        if ( deliveryNote == null ) {
            return null;
        }
        Long id = deliveryNote.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
