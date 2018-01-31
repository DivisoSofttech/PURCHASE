package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.QuotationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quotation and its DTO QuotationDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class, QuotationStatusMapper.class})
public interface QuotationMapper extends EntityMapper<QuotationDTO, Quotation> {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "quotationStatus.id", target = "quotationStatusId")
    QuotationDTO toDto(Quotation quotation);

    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "quotationStatusId", target = "quotationStatus")
    @Mapping(target = "quotationLines", ignore = true)
    Quotation toEntity(QuotationDTO quotationDTO);

    default Quotation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quotation quotation = new Quotation();
        quotation.setId(id);
        return quotation;
    }
}
