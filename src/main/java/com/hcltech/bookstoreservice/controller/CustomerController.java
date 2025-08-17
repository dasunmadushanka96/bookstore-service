package com.hcltech.bookstoreservice.controller;


import com.hcltech.bookstoreservice.dto.CustomerBookDTO;
import com.hcltech.bookstoreservice.dto.CustomerDTO;
import com.hcltech.bookstoreservice.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore-service/v1/customers")
public class CustomerController {

    Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll(){
        LOGGER.info("Retrieving details of all customers");
        return new ResponseEntity<List<CustomerDTO>>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CustomerBookDTO getOneById(@PathVariable int id){
        LOGGER.info("Retrieving customer with id: {}", id);
        return customerService.getOneByIdWithBooks(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customerDTO){
        LOGGER.info("Inside create method");
        return new ResponseEntity<>(customerService.create(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO customerDTO){
        LOGGER.info("Updating a customer with id: {}", customerDTO.getId());
        return new ResponseEntity<>(customerService.update(customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        LOGGER.info("Deleting a customer with id: {}", id);
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
