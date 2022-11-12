package com.anbaralabs;

import com.anbaralabs.entities.Product;
import com.anbaralabs.repositories.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * @author Ayoub Anbara
 */
@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ProductRepo productRepo) {
        return args -> {
            Product p1 = Product.builder()
                    .name("IPhone")
                    .price(BigDecimal.valueOf(2000))
                    .quantity(55)
                    .build();
            Product p2 = Product.builder()
                    .name("PC")
                    .price(BigDecimal.valueOf(3000))
                    .quantity(5)
                    .build();
            Product p3 = Product.builder()
                    .name("TV")
                    .price(BigDecimal.valueOf(6600))
                    .quantity(7)
                    .build();
            productRepo.save(p1);
            productRepo.save(p3);
            productRepo.save(p2);

        };
    }
}