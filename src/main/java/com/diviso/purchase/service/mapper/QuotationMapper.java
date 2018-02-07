package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.QuotationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quotation and its DTO QuotationDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class, StatusMapper.class})
public interface QuotationMapper extends EntityMapper<QuotationDTO, Quotation> {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "status.id", target = "statusId")
    QuotationDTO toDto(Quotation quotation);

    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(target = "quotationLines", ignore = true)
    @Mapping(source = "statusId", target = "status")
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
