package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Quotation;
import com.diviso.purchase.domain.QuotationLine;
import com.diviso.purchase.service.dto.QuotationLineDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-07T15:53:41+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class QuotationLineMapperImpl implements QuotationLineMapper {

    @Autowired
    private QuotationMapper quotationMapper;

    @Override
    public List<QuotationLine> toEntity(List<QuotationLineDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<QuotationLine> list = new ArrayList<QuotationLine>( dtoList.size() );
        for ( QuotationLineDTO quotationLineDTO : dtoList ) {
            list.add( toEntity( quotationLineDTO ) );
        }

        return list;
    }

    @Override
    public List<QuotationLineDTO> toDto(List<QuotationLine> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<QuotationLineDTO> list = new ArrayList<QuotationLineDTO>( entityList.size() );
        for ( QuotationLine quotationLine : entityList ) {
            list.add( toDto( quotationLine ) );
        }

        return list;
    }

    @Override
    public QuotationLineDTO toDto(QuotationLine quotationLine) {
        if ( quotationLine == null ) {
            return null;
        }

        QuotationLineDTO quotationLineDTO = new QuotationLineDTO();

        Long id = quotationLineQuotationId( quotationLine );
        if ( id != null ) {
            quotationLineDTO.setQuotationId( id );
        }
        quotationLineDTO.setId( quotationLine.getId() );
        quotationLineDTO.setReference( quotationLine.getReference() );
        quotationLineDTO.setPrice( quotationLine.getPrice() );
        quotationLineDTO.setTax( quotationLine.getTax() );
        quotationLineDTO.setAvailableQuantity( quotationLine.getAvailableQuantity() );
        quotationLineDTO.setIsSelect( quotationLine.isIsSelect() );

        return quotationLineDTO;
    }

    @Override
    public QuotationLine toEntity(QuotationLineDTO quotationLineDTO) {
        if ( quotationLineDTO == null ) {
            return null;
        }

        QuotationLine quotationLine = new QuotationLine();

        quotationLine.setQuotation( quotationMapper.fromId( quotationLineDTO.getQuotationId() ) );
        quotationLine.setId( quotationLineDTO.getId() );
        quotationLine.setReference( quotationLineDTO.getReference() );
        quotationLine.setPrice( quotationLineDTO.getPrice() );
        quotationLine.setTax( quotationLineDTO.getTax() );
        quotationLine.setAvailableQuantity( quotationLineDTO.getAvailableQuantity() );
        quotationLine.setIsSelect( quotationLineDTO.isIsSelect() );

        return quotationLine;
    }

    private Long quotationLineQuotationId(QuotationLine quotationLine) {
        if ( quotationLine == null ) {
            return null;
        }
        Quotation quotation = quotationLine.getQuotation();
        if ( quotation == null ) {
            return null;
        }
        Long id = quotation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
