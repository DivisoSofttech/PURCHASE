package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.DeliveriesLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeliveriesLine and its DTO DeliveriesLineDTO.
 */
@Mapper(componentModel = "spring", uses = {DeliveryNoteMapper.class})
public interface DeliveriesLineMapper extends EntityMapper<DeliveriesLineDTO, DeliveriesLine> {

    @Mapping(source = "deliveryNote.id", target = "deliveryNoteId")
    DeliveriesLineDTO toDto(DeliveriesLine deliveriesLine);

    @Mapping(source = "deliveryNoteId", target = "deliveryNote")
    DeliveriesLine toEntity(DeliveriesLineDTO deliveriesLineDTO);

    default DeliveriesLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeliveriesLine deliveriesLine = new DeliveriesLine();
        deliveriesLine.setId(id);
        return deliveriesLine;
    }
}
