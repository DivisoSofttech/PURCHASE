package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Alert;
import com.diviso.purchase.domain.Budget;
import com.diviso.purchase.service.dto.AlertDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-21T12:20:49+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class AlertMapperImpl implements AlertMapper {

    @Autowired
    private BudgetMapper budgetMapper;

    @Override
    public List<Alert> toEntity(List<AlertDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Alert> list = new ArrayList<Alert>( dtoList.size() );
        for ( AlertDTO alertDTO : dtoList ) {
            list.add( toEntity( alertDTO ) );
        }

        return list;
    }

    @Override
    public List<AlertDTO> toDto(List<Alert> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AlertDTO> list = new ArrayList<AlertDTO>( entityList.size() );
        for ( Alert alert : entityList ) {
            list.add( toDto( alert ) );
        }

        return list;
    }

    @Override
    public AlertDTO toDto(Alert alert) {
        if ( alert == null ) {
            return null;
        }

        AlertDTO alertDTO = new AlertDTO();

        Long id = alertBudgetId( alert );
        if ( id != null ) {
            alertDTO.setBudgetId( id );
        }
        alertDTO.setId( alert.getId() );
        alertDTO.setPercentage( alert.getPercentage() );
        alertDTO.setPrice( alert.getPrice() );

        return alertDTO;
    }

    @Override
    public Alert toEntity(AlertDTO alertDTO) {
        if ( alertDTO == null ) {
            return null;
        }

        Alert alert = new Alert();

        alert.setBudget( budgetMapper.fromId( alertDTO.getBudgetId() ) );
        alert.setId( alertDTO.getId() );
        alert.setPercentage( alertDTO.getPercentage() );
        alert.setPrice( alertDTO.getPrice() );

        return alert;
    }

    private Long alertBudgetId(Alert alert) {
        if ( alert == null ) {
            return null;
        }
        Budget budget = alert.getBudget();
        if ( budget == null ) {
            return null;
        }
        Long id = budget.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
