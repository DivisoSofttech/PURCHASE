package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Budget;
import com.diviso.purchase.service.dto.BudgetDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-16T12:13:29+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class BudgetMapperImpl implements BudgetMapper {

    @Override
    public BudgetDTO toDto(Budget entity) {
        if ( entity == null ) {
            return null;
        }

        BudgetDTO budgetDTO = new BudgetDTO();

        budgetDTO.setId( entity.getId() );
        budgetDTO.setReference( entity.getReference() );
        budgetDTO.setName( entity.getName() );
        budgetDTO.setPrice( entity.getPrice() );

        return budgetDTO;
    }

    @Override
    public List<Budget> toEntity(List<BudgetDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Budget> list = new ArrayList<Budget>( dtoList.size() );
        for ( BudgetDTO budgetDTO : dtoList ) {
            list.add( toEntity( budgetDTO ) );
        }

        return list;
    }

    @Override
    public List<BudgetDTO> toDto(List<Budget> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BudgetDTO> list = new ArrayList<BudgetDTO>( entityList.size() );
        for ( Budget budget : entityList ) {
            list.add( toDto( budget ) );
        }

        return list;
    }

    @Override
    public Budget toEntity(BudgetDTO budgetDTO) {
        if ( budgetDTO == null ) {
            return null;
        }

        Budget budget = new Budget();

        budget.setId( budgetDTO.getId() );
        budget.setReference( budgetDTO.getReference() );
        budget.setName( budgetDTO.getName() );
        budget.setPrice( budgetDTO.getPrice() );

        return budget;
    }
}
