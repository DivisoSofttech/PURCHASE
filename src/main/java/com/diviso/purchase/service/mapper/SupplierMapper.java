package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.*;
import com.diviso.purchase.service.dto.SupplierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Supplier and its DTO SupplierDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, ContactMapper.class, RatingMapper.class, BudgetMapper.class})
public interface SupplierMapper extends EntityMapper<SupplierDTO, Supplier> {

    @Mapping(source = "permanentAddress.id", target = "permanentAddressId")
    @Mapping(source = "contact.id", target = "contactId")
    @Mapping(source = "rating.id", target = "ratingId")
    @Mapping(source = "budget.id", target = "budgetId")
    SupplierDTO toDto(Supplier supplier);

    @Mapping(source = "permanentAddressId", target = "permanentAddress")
    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "ratingId", target = "rating")
    @Mapping(source = "budgetId", target = "budget")
    @Mapping(target = "temporaryAddresses", ignore = true)
    @Mapping(target = "purchaseOrders", ignore = true)
    @Mapping(target = "quatations", ignore = true)
    Supplier toEntity(SupplierDTO supplierDTO);

    default Supplier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Supplier supplier = new Supplier();
        supplier.setId(id);
        return supplier;
    }
}
