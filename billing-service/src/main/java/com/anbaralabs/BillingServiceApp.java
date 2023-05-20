package com.anbaralabs;

import com.anbaralabs.entities.Bill;
import com.anbaralabs.entities.ProductItem;
import com.anbaralabs.feign.CustomerRestClient;
import com.anbaralabs.feign.ProductItemRestClient;
import com.anbaralabs.model.Customer;
import com.anbaralabs.model.Product;
import com.anbaralabs.repositories.BillRepository;
import com.anbaralabs.repositories.ProductItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

/**
 * @author Ayoub Anbara
 */
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class BillingServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BillRepository billRepository,
                                               ProductItemRepository productItemRepository,
                                               CustomerRestClient customerRestClient,
                                               ProductItemRestClient productItemRestClient) {
        return args -> {
            Customer customer = customerRestClient.findCustomerById(1L);
            log.info("customer email: {}", customer.getEmail());
            Bill billSaved = billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));

            PagedModel<Product> productPagedModel = productItemRestClient.findAll();
            productPagedModel.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1 + new Random().nextInt());
                productItem.setBill(null);
                productItem.setProductID(product.getId());
                productItemRepository.save(productItem);
            });

        };
    }
}