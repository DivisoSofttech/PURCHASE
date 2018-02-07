package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Address;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.service.dto.AddressDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-07T15:53:41+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Address> toEntity(List<AddressDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Address> list = new ArrayList<Address>( dtoList.size() );
        for ( AddressDTO addressDTO : dtoList ) {
            list.add( toEntity( addressDTO ) );
        }

        return list;
    }

    @Override
    public List<AddressDTO> toDto(List<Address> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AddressDTO> list = new ArrayList<AddressDTO>( entityList.size() );
        for ( Address address : entityList ) {
            list.add( toDto( address ) );
        }

        return list;
    }

    @Override
    public AddressDTO toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        Long id = addressSupplierId( address );
        if ( id != null ) {
            addressDTO.setSupplierId( id );
        }
        addressDTO.setId( address.getId() );
        addressDTO.setPlace( address.getPlace() );
        addressDTO.setDistrict( address.getDistrict() );
        addressDTO.setState( address.getState() );
        addressDTO.setPinCode( address.getPinCode() );

        return addressDTO;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setSupplier( supplierMapper.fromId( addressDTO.getSupplierId() ) );
        address.setId( addressDTO.getId() );
        address.setPlace( addressDTO.getPlace() );
        address.setDistrict( addressDTO.getDistrict() );
        address.setState( addressDTO.getState() );
        address.setPinCode( addressDTO.getPinCode() );

        return address;
    }

    private Long addressSupplierId(Address address) {
        if ( address == null ) {
            return null;
        }
        Supplier supplier = address.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        Long id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
