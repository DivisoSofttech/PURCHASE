package com.diviso.purchase.repository;

import com.diviso.purchase.domain.Supplier;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Supplier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Supplier findByFirstName(String firstName);

	

}