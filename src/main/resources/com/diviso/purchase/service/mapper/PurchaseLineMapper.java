package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.PurchaseLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PurchaseLine and its DTO PurchaseLineDTO.
 */
@Mapper(componentModel = "spring", uses = {PurchaseOrderMapper.class})
public interface PurchaseLineMapper extends EntityMapper<PurchaseLineDTO, PurchaseLine> {

    @Mapping(source = "purchaseOrder.id", target = "purchaseOrderId")
    PurchaseLineDTO toDto(PurchaseLine purchaseLine);

    @Mapping(source = "purchaseOrderId", target = "purchaseOrder")
    PurchaseLine toEntity(PurchaseLineDTO purchaseLineDTO);

    default PurchaseLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseLine purchaseLine = new PurchaseLine();
        purchaseLine.setId(id);
        return purchaseLine;
    }
}
