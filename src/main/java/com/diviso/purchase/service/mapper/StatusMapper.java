package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.StatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Status and its DTO StatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {


    @Mapping(target = "quotations", ignore = true)
    @Mapping(target = "purchaseOrders", ignore = true)
    Status toEntity(StatusDTO statusDTO);

    default Status fromId(Long id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setId(id);
        return status;
    }
}
