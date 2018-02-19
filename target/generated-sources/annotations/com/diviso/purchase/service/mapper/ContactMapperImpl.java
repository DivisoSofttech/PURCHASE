package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Contact;
import com.diviso.purchase.service.dto.ContactDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-17T13:52:42+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact toEntity(ContactDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setId( dto.getId() );
        contact.setMailId( dto.getMailId() );
        contact.setPhoneNumber1( dto.getPhoneNumber1() );
        contact.setPhoneNumber2( dto.getPhoneNumber2() );
        contact.setCompanyName( dto.getCompanyName() );

        return contact;
    }

    @Override
    public ContactDTO toDto(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setId( entity.getId() );
        contactDTO.setMailId( entity.getMailId() );
        contactDTO.setPhoneNumber1( entity.getPhoneNumber1() );
        contactDTO.setPhoneNumber2( entity.getPhoneNumber2() );
        contactDTO.setCompanyName( entity.getCompanyName() );

        return contactDTO;
    }

    @Override
    public List<Contact> toEntity(List<ContactDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Contact> list = new ArrayList<Contact>( dtoList.size() );
        for ( ContactDTO contactDTO : dtoList ) {
            list.add( toEntity( contactDTO ) );
        }

        return list;
    }

    @Override
    public List<ContactDTO> toDto(List<Contact> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContactDTO> list = new ArrayList<ContactDTO>( entityList.size() );
        for ( Contact contact : entityList ) {
            list.add( toDto( contact ) );
        }

        return list;
    }
}
