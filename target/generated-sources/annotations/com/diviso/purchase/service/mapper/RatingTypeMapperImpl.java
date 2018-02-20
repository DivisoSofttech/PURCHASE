package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.RatingType;
import com.diviso.purchase.service.dto.RatingTypeDTO;
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
public class RatingTypeMapperImpl implements RatingTypeMapper {

    @Override
    public RatingType toEntity(RatingTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RatingType ratingType = new RatingType();

        ratingType.setId( dto.getId() );
        ratingType.setReference( dto.getReference() );
        ratingType.setType( dto.getType() );

        return ratingType;
    }

    @Override
    public RatingTypeDTO toDto(RatingType entity) {
        if ( entity == null ) {
            return null;
        }

        RatingTypeDTO ratingTypeDTO = new RatingTypeDTO();

        ratingTypeDTO.setId( entity.getId() );
        ratingTypeDTO.setReference( entity.getReference() );
        ratingTypeDTO.setType( entity.getType() );

        return ratingTypeDTO;
    }

    @Override
    public List<RatingType> toEntity(List<RatingTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RatingType> list = new ArrayList<RatingType>( dtoList.size() );
        for ( RatingTypeDTO ratingTypeDTO : dtoList ) {
            list.add( toEntity( ratingTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<RatingTypeDTO> toDto(List<RatingType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RatingTypeDTO> list = new ArrayList<RatingTypeDTO>( entityList.size() );
        for ( RatingType ratingType : entityList ) {
            list.add( toDto( ratingType ) );
        }

        return list;
    }
}
