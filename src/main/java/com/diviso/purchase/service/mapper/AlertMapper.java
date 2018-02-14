package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.AlertDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Alert and its DTO AlertDTO.
 */
@Mapper(componentModel = "spring", uses = {BudgetMapper.class})
public interface AlertMapper extends EntityMapper<AlertDTO, Alert> {

    @Mapping(source = "budget.id", target = "budgetId")
    AlertDTO toDto(Alert alert);

    @Mapping(source = "budgetId", target = "budget")
    Alert toEntity(AlertDTO alertDTO);

    default Alert fromId(Long id) {
        if (id == null) {
            return null;
        }
        Alert alert = new Alert();
        alert.setId(id);
        return alert;
    }
}
