package com.diviso.purchase.repository;

import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the DeliveryNote entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
    @Query("select distinct delivery_note from DeliveryNote delivery_note left join fetch delivery_note.ratings")
    List<DeliveryNote> findAllWithEagerRelationships();

    @Query("select delivery_note from DeliveryNote delivery_note left join fetch delivery_note.ratings where delivery_note.id =:id")
    DeliveryNote findOneWithEagerRelationships(@Param("id") Long id);
   

	Page<DeliveryNote> findByPurchaseDate(LocalDate purchaseDate, Pageable pageable);

}
