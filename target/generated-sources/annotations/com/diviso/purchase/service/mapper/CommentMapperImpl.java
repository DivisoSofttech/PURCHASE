package com.diviso.purchase.service.mapper;

import com.diviso.purchase.domain.Comment;
import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.service.dto.CommentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-17T13:52:41+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private DeliveryNoteMapper deliveryNoteMapper;

    @Override
    public List<Comment> toEntity(List<CommentDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( dtoList.size() );
        for ( CommentDTO commentDTO : dtoList ) {
            list.add( toEntity( commentDTO ) );
        }

        return list;
    }

    @Override
    public List<CommentDTO> toDto(List<Comment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( entityList.size() );
        for ( Comment comment : entityList ) {
            list.add( toDto( comment ) );
        }

        return list;
    }

    @Override
    public CommentDTO toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        Long id = commentDeliveryNoteId( comment );
        if ( id != null ) {
            commentDTO.setDeliveryNoteId( id );
        }
        commentDTO.setId( comment.getId() );
        commentDTO.setReference( comment.getReference() );
        commentDTO.setComments( comment.getComments() );

        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setDeliveryNote( deliveryNoteMapper.fromId( commentDTO.getDeliveryNoteId() ) );
        comment.setId( commentDTO.getId() );
        comment.setReference( commentDTO.getReference() );
        comment.setComments( commentDTO.getComments() );

        return comment;
    }

    private Long commentDeliveryNoteId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        DeliveryNote deliveryNote = comment.getDeliveryNote();
        if ( deliveryNote == null ) {
            return null;
        }
        Long id = deliveryNote.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
