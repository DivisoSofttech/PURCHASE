package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.DeliveryLine;
import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.service.dto.DeliveryLineDTO;
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
public class DeliveryLineMapperImpl implements DeliveryLineMapper {

    @Autowired
    private DeliveryNoteMapper deliveryNoteMapper;

    @Override
    public List<DeliveryLine> toEntity(List<DeliveryLineDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DeliveryLine> list = new ArrayList<DeliveryLine>( dtoList.size() );
        for ( DeliveryLineDTO deliveryLineDTO : dtoList ) {
            list.add( toEntity( deliveryLineDTO ) );
        }

        return list;
    }

    @Override
    public List<DeliveryLineDTO> toDto(List<DeliveryLine> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DeliveryLineDTO> list = new ArrayList<DeliveryLineDTO>( entityList.size() );
        for ( DeliveryLine deliveryLine : entityList ) {
            list.add( toDto( deliveryLine ) );
        }

        return list;
    }

    @Override
    public DeliveryLineDTO toDto(DeliveryLine deliveryLine) {
        if ( deliveryLine == null ) {
            return null;
        }

        DeliveryLineDTO deliveryLineDTO = new DeliveryLineDTO();

        Long id = deliveryLineDeliveryNoteId( deliveryLine );
        if ( id != null ) {
            deliveryLineDTO.setDeliveryNoteId( id );
        }
        deliveryLineDTO.setId( deliveryLine.getId() );
        deliveryLineDTO.setReference( deliveryLine.getReference() );
        deliveryLineDTO.setPrice( deliveryLine.getPrice() );
        deliveryLineDTO.setTax( deliveryLine.getTax() );
        deliveryLineDTO.setQuantity( deliveryLine.getQuantity() );

        return deliveryLineDTO;
    }

    @Override
    public DeliveryLine toEntity(DeliveryLineDTO deliveryLineDTO) {
        if ( deliveryLineDTO == null ) {
            return null;
        }

        DeliveryLine deliveryLine = new DeliveryLine();

        deliveryLine.setDeliveryNote( deliveryNoteMapper.fromId( deliveryLineDTO.getDeliveryNoteId() ) );
        deliveryLine.setId( deliveryLineDTO.getId() );
        deliveryLine.setReference( deliveryLineDTO.getReference() );
        deliveryLine.setPrice( deliveryLineDTO.getPrice() );
        deliveryLine.setTax( deliveryLineDTO.getTax() );
        deliveryLine.setQuantity( deliveryLineDTO.getQuantity() );

        return deliveryLine;
    }

    private Long deliveryLineDeliveryNoteId(DeliveryLine deliveryLine) {
        if ( deliveryLine == null ) {
            return null;
        }
        DeliveryNote deliveryNote = deliveryLine.getDeliveryNote();
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
