package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.QuotationStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotationStatus and its DTO QuotationStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuotationStatusMapper extends EntityMapper<QuotationStatusDTO, QuotationStatus> {



    default QuotationStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotationStatus quotationStatus = new QuotationStatus();
        quotationStatus.setId(id);
        return quotationStatus;
    }
}
