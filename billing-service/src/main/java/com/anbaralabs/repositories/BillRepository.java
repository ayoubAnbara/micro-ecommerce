package com.anbaralabs.repositories;

import com.anbaralabs.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Ayoub Anbara
 */
@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill, Long> {
}
