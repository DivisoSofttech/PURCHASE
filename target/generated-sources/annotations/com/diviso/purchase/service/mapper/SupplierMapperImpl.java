package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Address;
import com.diviso.purchase.domain.Contact;
import com.diviso.purchase.domain.Supplier;
import com.diviso.purchase.service.dto.SupplierDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-07T17:15:40+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<Supplier> toEntity(List<SupplierDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Supplier> list = new ArrayList<Supplier>( dtoList.size() );
        for ( SupplierDTO supplierDTO : dtoList ) {
            list.add( toEntity( supplierDTO ) );
        }

        return list;
    }

    @Override
    public List<SupplierDTO> toDto(List<Supplier> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SupplierDTO> list = new ArrayList<SupplierDTO>( entityList.size() );
        for ( Supplier supplier : entityList ) {
            list.add( toDto( supplier ) );
        }

        return list;
    }

    @Override
    public SupplierDTO toDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDTO supplierDTO = new SupplierDTO();

        Long id = supplierContactId( supplier );
        if ( id != null ) {
            supplierDTO.setContactId( id );
        }
        Long id1 = supplierPermanentAddressId( supplier );
        if ( id1 != null ) {
            supplierDTO.setPermanentAddressId( id1 );
        }
        supplierDTO.setId( supplier.getId() );
        supplierDTO.setReference( supplier.getReference() );
        supplierDTO.setFirstName( supplier.getFirstName() );
        supplierDTO.setLastName( supplier.getLastName() );

        return supplierDTO;
    }

    @Override
    public Supplier toEntity(SupplierDTO supplierDTO) {
        if ( supplierDTO == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setPermanentAddress( addressMapper.fromId( supplierDTO.getPermanentAddressId() ) );
        supplier.setContact( contactMapper.fromId( supplierDTO.getContactId() ) );
        supplier.setId( supplierDTO.getId() );
        supplier.setReference( supplierDTO.getReference() );
        supplier.setFirstName( supplierDTO.getFirstName() );
        supplier.setLastName( supplierDTO.getLastName() );

        return supplier;
    }

    private Long supplierContactId(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Contact contact = supplier.getContact();
        if ( contact == null ) {
            return null;
        }
        Long id = contact.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long supplierPermanentAddressId(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Address permanentAddress = supplier.getPermanentAddress();
        if ( permanentAddress == null ) {
            return null;
        }
        Long id = permanentAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
