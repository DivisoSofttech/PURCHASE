package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.BudgetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Budget and its DTO BudgetDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BudgetMapper extends EntityMapper<BudgetDTO, Budget> {


    @Mapping(target = "alerts", ignore = true)
    Budget toEntity(BudgetDTO budgetDTO);

    default Budget fromId(Long id) {
        if (id == null) {
            return null;
        }
        Budget budget = new Budget();
        budget.setId(id);
        return budget;
    }
}
