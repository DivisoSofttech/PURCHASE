package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.RatingTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RatingType and its DTO RatingTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {RatingMapper.class})
public interface RatingTypeMapper extends EntityMapper<RatingTypeDTO, RatingType> {

    @Mapping(source = "rating.id", target = "ratingId")
    RatingTypeDTO toDto(RatingType ratingType);

    @Mapping(source = "ratingId", target = "rating")
    RatingType toEntity(RatingTypeDTO ratingTypeDTO);

    default RatingType fromId(Long id) {
        if (id == null) {
            return null;
        }
        RatingType ratingType = new RatingType();
        ratingType.setId(id);
        return ratingType;
    }
}
