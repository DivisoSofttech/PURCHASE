package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Quotation;
import com.diviso.purchase.domain.Status;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.service.dto.QuotationDTO;
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
public class QuotationMapperImpl implements QuotationMapper {

    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private StatusMapper statusMapper;

    @Override
    public List<Quotation> toEntity(List<QuotationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Quotation> list = new ArrayList<Quotation>( dtoList.size() );
        for ( QuotationDTO quotationDTO : dtoList ) {
            list.add( toEntity( quotationDTO ) );
        }

        return list;
    }

    @Override
    public List<QuotationDTO> toDto(List<Quotation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<QuotationDTO> list = new ArrayList<QuotationDTO>( entityList.size() );
        for ( Quotation quotation : entityList ) {
            list.add( toDto( quotation ) );
        }

        return list;
    }

    @Override
    public QuotationDTO toDto(Quotation quotation) {
        if ( quotation == null ) {
            return null;
        }

        QuotationDTO quotationDTO = new QuotationDTO();

        Long id = quotationStatusId( quotation );
        if ( id != null ) {
            quotationDTO.setStatusId( id );
        }
        Long id1 = quotationSupplierId( quotation );
        if ( id1 != null ) {
            quotationDTO.setSupplierId( id1 );
        }
        quotationDTO.setId( quotation.getId() );
        quotationDTO.setReference( quotation.getReference() );
        quotationDTO.setIssuedDate( quotation.getIssuedDate() );

        return quotationDTO;
    }

    @Override
    public Quotation toEntity(QuotationDTO quotationDTO) {
        if ( quotationDTO == null ) {
            return null;
        }

        Quotation quotation = new Quotation();

        quotation.setSupplier( supplierMapper.fromId( quotationDTO.getSupplierId() ) );
        quotation.setStatus( statusMapper.fromId( quotationDTO.getStatusId() ) );
        quotation.setId( quotationDTO.getId() );
        quotation.setReference( quotationDTO.getReference() );
        quotation.setIssuedDate( quotationDTO.getIssuedDate() );

        return quotation;
    }

    private Long quotationStatusId(Quotation quotation) {
        if ( quotation == null ) {
            return null;
        }
        Status status = quotation.getStatus();
        if ( status == null ) {
            return null;
        }
        Long id = status.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long quotationSupplierId(Quotation quotation) {
        if ( quotation == null ) {
            return null;
        }
        Supplier supplier = quotation.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        Long id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
