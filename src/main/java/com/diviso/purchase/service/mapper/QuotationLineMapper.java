package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.QuotationLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationLine and its DTO QuotationLineDTO.
 */
@Mapper(componentModel = "spring", uses = {QuotationMapper.class})
public interface QuotationLineMapper extends EntityMapper<QuotationLineDTO, QuotationLine> {

    @Mapping(source = "quotation.id", target = "quotationId")
    QuotationLineDTO toDto(QuotationLine quotationLine);

    @Mapping(source = "quotationId", target = "quotation")
    QuotationLine toEntity(QuotationLineDTO quotationLineDTO);

    default QuotationLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationLine quotationLine = new QuotationLine();
        quotationLine.setId(id);
        return quotationLine;
    }
}
