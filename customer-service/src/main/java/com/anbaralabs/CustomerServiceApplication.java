package com.anbaralabs;

import com.anbaralabs.entities.Customer;
import com.anbaralabs.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

/**
 * @author Ayoub Anbara
 */
@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepo customerRepo, RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            Customer c1 = Customer.builder()
                    .name("ayoub anbara")
                    .email("anbara.ayoub@gmail.com")
                    .build();
            Customer c2 = Customer.builder()
                    .name("amin")
                    .email("amin@gmail.com")
                    .build();
            Customer c3 = Customer.builder()
                    .name("yahay")
                    .email("anbara.aypub@gmail.com")
                    .build();
            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);
        };
    }
}