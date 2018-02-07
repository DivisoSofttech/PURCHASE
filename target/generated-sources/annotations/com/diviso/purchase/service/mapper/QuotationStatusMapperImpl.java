package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.QuotationStatus;
import com.diviso.purchase.service.dto.QuotationStatusDTO;
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
public class QuotationStatusMapperImpl implements QuotationStatusMapper {

    @Override
    public QuotationStatus toEntity(QuotationStatusDTO dto) {
        if ( dto == null ) {
            return null;
        }

        QuotationStatus quotationStatus = new QuotationStatus();

        quotationStatus.setId( dto.getId() );
        quotationStatus.setStatusId( dto.getStatusId() );
        quotationStatus.setStatusLevel( dto.getStatusLevel() );

        return quotationStatus;
    }

    @Override
    public QuotationStatusDTO toDto(QuotationStatus entity) {
        if ( entity == null ) {
            return null;
        }

        QuotationStatusDTO quotationStatusDTO = new QuotationStatusDTO();

        quotationStatusDTO.setId( entity.getId() );
        quotationStatusDTO.setStatusId( entity.getStatusId() );
        quotationStatusDTO.setStatusLevel( entity.getStatusLevel() );

        return quotationStatusDTO;
    }

    @Override
    public List<QuotationStatus> toEntity(List<QuotationStatusDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<QuotationStatus> list = new ArrayList<QuotationStatus>( dtoList.size() );
        for ( QuotationStatusDTO quotationStatusDTO : dtoList ) {
            list.add( toEntity( quotationStatusDTO ) );
        }

        return list;
    }

    @Override
    public List<QuotationStatusDTO> toDto(List<QuotationStatus> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<QuotationStatusDTO> list = new ArrayList<QuotationStatusDTO>( entityList.size() );
        for ( QuotationStatus quotationStatus : entityList ) {
            list.add( toDto( quotationStatus ) );
        }

        return list;
    }
}
