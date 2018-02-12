package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.StatussDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Statuss and its DTO StatussDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatussMapper extends EntityMapper<StatussDTO, Statuss> {


    @Mapping(target = "quotations", ignore = true)
    @Mapping(target = "purchaseOrders", ignore = true)
    Statuss toEntity(StatussDTO statussDTO);

    default Statuss fromId(Long id) {
        if (id == null) {
            return null;
        }
        Statuss statuss = new Statuss();
        statuss.setId(id);
        return statuss;
    }
}
