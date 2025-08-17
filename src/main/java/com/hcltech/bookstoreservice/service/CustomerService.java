package com.hcltech.bookstoreservice.service;

import com.hcltech.bookstoreservice.dto.CustomerBookDTO;
import com.hcltech.bookstoreservice.dto.CustomerDTO;
import com.hcltech.bookstoreservice.exception.BadRequestException;
import com.hcltech.bookstoreservice.mapper.CustomerMapper;
import com.hcltech.bookstoreservice.model.Customer;
import com.hcltech.bookstoreservice.model.Order;
import com.hcltech.bookstoreservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAll(){
        LOGGER.info("Retrieving all customer details.");
        return customerRepository.findAll().stream().map(customer -> customerMapper.toDto(customer)).collect(Collectors.toList());
    }

    public CustomerBookDTO getOneByIdWithBooks(int id){

        LOGGER.info("Retrieving details of customer with id: "+id);

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->new BadRequestException("Cannot find Customer with id "+id));
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        List<Order> orders = customer.getOrders();
        Set<String> purchasedBooks = new HashSet<>();
        for (Order order : orders) {
            purchasedBooks = order.getPurchasedBookList().stream().map(book -> book.getBook().getTitle()).collect(Collectors.toSet());
        }
        return new CustomerBookDTO(customerDTO, purchasedBooks);
    }

    public Customer getOneById(int id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }


    public CustomerDTO create(CustomerDTO customerDTO){
        Customer savedCustomer = customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerMapper.toDto(savedCustomer);
    }


    public CustomerDTO update(CustomerDTO customerDTO){
        if(!customerRepository.existsById(customerDTO.getId())){
            throw new BadRequestException("Cannot find entry with id "+ customerDTO.getId());
        }
        LOGGER.info("Updating details of customer with id: "+customerDTO.getId());
        Customer savedCustomer = customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerMapper.toDto(savedCustomer);
    }


    public void delete(int id){
        if(!customerRepository.existsById(id)){
            throw new BadRequestException("Cannot find entry with id "+id);
        }
        LOGGER.info("Deleting a customer with id: "+id);
        customerRepository.deleteById(id);
    }

}
