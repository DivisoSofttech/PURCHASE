package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.DeliveryLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeliveryLine and its DTO DeliveryLineDTO.
 */
@Mapper(componentModel = "spring", uses = {DeliveryNoteMapper.class})
public interface DeliveryLineMapper extends EntityMapper<DeliveryLineDTO, DeliveryLine> {

    @Mapping(source = "deliveryNote.id", target = "deliveryNoteId")
    DeliveryLineDTO toDto(DeliveryLine deliveryLine);

    @Mapping(source = "deliveryNoteId", target = "deliveryNote")
    DeliveryLine toEntity(DeliveryLineDTO deliveryLineDTO);

    default DeliveryLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeliveryLine deliveryLine = new DeliveryLine();
        deliveryLine.setId(id);
        return deliveryLine;
    }
}
