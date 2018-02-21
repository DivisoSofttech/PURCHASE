package com.diviso.purchase.repository;

import com.diviso.purchase.domain.DeliveryNote;
import org.springframework.stereotype.Repository;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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

	Page<DeliveryNote> findAllByReference(String reference, Pageable pageable);

	Page<DeliveryNote> findAllByDeliveredDate(LocalDate purchaseDate, Pageable pageable);

	Page<DeliveryNote> findAllByDeliveredDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

	Page<DeliveryNote> findAllBySupplier_Id(Long supplierId, Pageable pageable);

	Page<DeliveryNote> findAllByOrderReference(String orderReference, Pageable pageable);

}
