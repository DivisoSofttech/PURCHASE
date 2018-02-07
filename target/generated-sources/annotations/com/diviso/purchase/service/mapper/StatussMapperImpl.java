package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Statuss;
import com.diviso.purchase.service.dto.StatussDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-07T16:42:26+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class StatussMapperImpl implements StatussMapper {

    @Override
    public StatussDTO toDto(Statuss entity) {
        if ( entity == null ) {
            return null;
        }

        StatussDTO statussDTO = new StatussDTO();

        statussDTO.setId( entity.getId() );
        statussDTO.setName( entity.getName() );
        statussDTO.setStatusLevel( entity.getStatusLevel() );

        return statussDTO;
    }

    @Override
    public List<Statuss> toEntity(List<StatussDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Statuss> list = new ArrayList<Statuss>( dtoList.size() );
        for ( StatussDTO statussDTO : dtoList ) {
            list.add( toEntity( statussDTO ) );
        }

        return list;
    }

    @Override
    public List<StatussDTO> toDto(List<Statuss> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StatussDTO> list = new ArrayList<StatussDTO>( entityList.size() );
        for ( Statuss statuss : entityList ) {
            list.add( toDto( statuss ) );
        }

        return list;
    }

    @Override
    public Statuss toEntity(StatussDTO statussDTO) {
        if ( statussDTO == null ) {
            return null;
        }

        Statuss statuss = new Statuss();

        statuss.setId( statussDTO.getId() );
        statuss.setName( statussDTO.getName() );
        statuss.setStatusLevel( statussDTO.getStatusLevel() );

        return statuss;
    }
}
