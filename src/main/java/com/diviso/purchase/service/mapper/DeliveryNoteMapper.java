package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeliveryNote and its DTO DeliveryNoteDTO.
 */
@Mapper(componentModel = "spring", uses = {PurchaseOrderMapper.class, RatingMapper.class})
public interface DeliveryNoteMapper extends EntityMapper<DeliveryNoteDTO, DeliveryNote> {

    @Mapping(source = "purchaseOrder.id", target = "purchaseOrderId")
    @Mapping(source = "rating.id", target = "ratingId")
    DeliveryNoteDTO toDto(DeliveryNote deliveryNote);

    @Mapping(source = "purchaseOrderId", target = "purchaseOrder")
    @Mapping(source = "ratingId", target = "rating")
    @Mapping(target = "comments", ignore = true)
    DeliveryNote toEntity(DeliveryNoteDTO deliveryNoteDTO);

    default DeliveryNote fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setId(id);
        return deliveryNote;
    }
}
