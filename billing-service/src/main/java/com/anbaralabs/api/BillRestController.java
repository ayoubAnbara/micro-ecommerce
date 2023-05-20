package com.anbaralabs.api;

import com.anbaralabs.entities.Bill;
import com.anbaralabs.feign.CustomerRestClient;
import com.anbaralabs.feign.ProductItemRestClient;
import com.anbaralabs.repositories.BillRepository;
import com.anbaralabs.repositories.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Ayoub Anbara
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bills/")
public class BillRestController {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductItemRestClient productItemRestClient;

    @GetMapping("full/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                String.format("Resource with id= %s not found", id)));
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(productItemRestClient.findProductById(pi.getProductID()));
        });
        return bill;
    }
}
