package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeliveryNote and its DTO DeliveryNoteDTO.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class, RatingMapper.class, RatingTypeMapper.class})
public interface DeliveryNoteMapper extends EntityMapper<DeliveryNoteDTO, DeliveryNote> {

    @Mapping(source = "supplier.id", target = "supplierId")
    DeliveryNoteDTO toDto(DeliveryNote deliveryNote);

    @Mapping(source = "supplierId", target = "supplier")
    @Mapping(target = "deliveryLines", ignore = true)
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
