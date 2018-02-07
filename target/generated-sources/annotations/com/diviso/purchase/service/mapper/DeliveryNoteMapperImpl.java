package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.domain.Rating;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import com.diviso.purchase.service.dto.RatingDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-07T16:42:27+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class DeliveryNoteMapperImpl implements DeliveryNoteMapper {

    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public DeliveryNoteDTO toDto(DeliveryNote entity) {
        if ( entity == null ) {
            return null;
        }

        DeliveryNoteDTO deliveryNoteDTO = new DeliveryNoteDTO();

        deliveryNoteDTO.setId( entity.getId() );
        deliveryNoteDTO.setReference( entity.getReference() );
        deliveryNoteDTO.setOrderReference( entity.getOrderReference() );
        deliveryNoteDTO.setPurchaseDate( entity.getPurchaseDate() );
        deliveryNoteDTO.setRatings( ratingSetToRatingDTOSet( entity.getRatings() ) );

        return deliveryNoteDTO;
    }

    @Override
    public List<DeliveryNote> toEntity(List<DeliveryNoteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DeliveryNote> list = new ArrayList<DeliveryNote>( dtoList.size() );
        for ( DeliveryNoteDTO deliveryNoteDTO : dtoList ) {
            list.add( toEntity( deliveryNoteDTO ) );
        }

        return list;
    }

    @Override
    public List<DeliveryNoteDTO> toDto(List<DeliveryNote> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DeliveryNoteDTO> list = new ArrayList<DeliveryNoteDTO>( entityList.size() );
        for ( DeliveryNote deliveryNote : entityList ) {
            list.add( toDto( deliveryNote ) );
        }

        return list;
    }

    @Override
    public DeliveryNote toEntity(DeliveryNoteDTO deliveryNoteDTO) {
        if ( deliveryNoteDTO == null ) {
            return null;
        }

        DeliveryNote deliveryNote = new DeliveryNote();

        deliveryNote.setId( deliveryNoteDTO.getId() );
        deliveryNote.setReference( deliveryNoteDTO.getReference() );
        deliveryNote.setOrderReference( deliveryNoteDTO.getOrderReference() );
        deliveryNote.setPurchaseDate( deliveryNoteDTO.getPurchaseDate() );
        deliveryNote.setRatings( ratingDTOSetToRatingSet( deliveryNoteDTO.getRatings() ) );

        return deliveryNote;
    }

    protected Set<RatingDTO> ratingSetToRatingDTOSet(Set<Rating> set) {
        if ( set == null ) {
            return null;
        }

        Set<RatingDTO> set1 = new HashSet<RatingDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Rating rating : set ) {
            set1.add( ratingMapper.toDto( rating ) );
        }

        return set1;
    }

    protected Set<Rating> ratingDTOSetToRatingSet(Set<RatingDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Rating> set1 = new HashSet<Rating>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RatingDTO ratingDTO : set ) {
            set1.add( ratingMapper.toEntity( ratingDTO ) );
        }

        return set1;
    }
}
