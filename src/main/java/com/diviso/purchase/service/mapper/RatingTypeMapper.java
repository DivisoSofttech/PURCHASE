package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.RatingTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RatingType and its DTO RatingTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RatingTypeMapper extends EntityMapper<RatingTypeDTO, RatingType> {



    default RatingType fromId(Long id) {
        if (id == null) {
            return null;
        }
        RatingType ratingType = new RatingType();
        ratingType.setId(id);
        return ratingType;
    }
}
