package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.PurchaseOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PurchaseOrder and its DTO PurchaseOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class, StatussMapper.class})
public interface PurchaseOrderMapper extends EntityMapper<PurchaseOrderDTO, PurchaseOrder> {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "statuss.id", target = "statussId")
    PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder);

    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(source = "statussId", target = "statuss")
    @Mapping(target = "purchaseLines", ignore = true)
    PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);

    default PurchaseOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(id);
        return purchaseOrder;
    }
}
