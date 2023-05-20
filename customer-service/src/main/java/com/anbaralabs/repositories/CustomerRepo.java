package com.anbaralabs.repositories;

import com.anbaralabs.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Ayoub Anbara
 */
@RepositoryRestResource
public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
