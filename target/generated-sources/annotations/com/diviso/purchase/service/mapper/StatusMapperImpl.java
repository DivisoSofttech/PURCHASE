package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Status;
import com.diviso.purchase.service.dto.StatusDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-02T16:10:42+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public StatusDTO toDto(Status entity) {
        if ( entity == null ) {
            return null;
        }

        StatusDTO statusDTO = new StatusDTO();

        statusDTO.setId( entity.getId() );
        statusDTO.setName( entity.getName() );
        statusDTO.setStatusLevel( entity.getStatusLevel() );

        return statusDTO;
    }

    @Override
    public List<Status> toEntity(List<StatusDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Status> list = new ArrayList<Status>( dtoList.size() );
        for ( StatusDTO statusDTO : dtoList ) {
            list.add( toEntity( statusDTO ) );
        }

        return list;
    }

    @Override
    public List<StatusDTO> toDto(List<Status> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StatusDTO> list = new ArrayList<StatusDTO>( entityList.size() );
        for ( Status status : entityList ) {
            list.add( toDto( status ) );
        }

        return list;
    }

    @Override
    public Status toEntity(StatusDTO statusDTO) {
        if ( statusDTO == null ) {
            return null;
        }

        Status status = new Status();

        status.setId( statusDTO.getId() );
        status.setName( statusDTO.getName() );
        status.setStatusLevel( statusDTO.getStatusLevel() );

        return status;
    }
}
