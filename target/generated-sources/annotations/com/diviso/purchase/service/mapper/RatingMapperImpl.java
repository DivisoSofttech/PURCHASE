package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Rating;
import com.diviso.purchase.service.dto.RatingDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-20T11:19:02+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

    @Override
    public Rating toEntity(RatingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Rating rating = new Rating();

        rating.setId( dto.getId() );
        rating.setReference( dto.getReference() );
        rating.setRatings( dto.getRatings() );

        return rating;
    }

    @Override
    public RatingDTO toDto(Rating entity) {
        if ( entity == null ) {
            return null;
        }

        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setId( entity.getId() );
        ratingDTO.setReference( entity.getReference() );
        ratingDTO.setRatings( entity.getRatings() );

        return ratingDTO;
    }

    @Override
    public List<Rating> toEntity(List<RatingDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Rating> list = new ArrayList<Rating>( dtoList.size() );
        for ( RatingDTO ratingDTO : dtoList ) {
            list.add( toEntity( ratingDTO ) );
        }

        return list;
    }

    @Override
    public List<RatingDTO> toDto(List<Rating> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RatingDTO> list = new ArrayList<RatingDTO>( entityList.size() );
        for ( Rating rating : entityList ) {
            list.add( toDto( rating ) );
        }

        return list;
    }
}
