package com.hcltech.bookstoreservice.RepositoryTest;


import com.hcltech.bookstoreservice.model.Customer;
import com.hcltech.bookstoreservice.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @org.junit.jupiter.api.Order(1)
    void testSaveAndFindCustomer() {

        Customer customer = new Customer();

        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("jd@gmail.com");
        customer.setPhone("1234567890");
        customerRepository.save(customer);
        LoggerFactory.getLogger(CustomerRepositoryTest.class).info("Customer saved successfully: " + customer.getFirstName() + " " + customer.getLastName());

        List<Customer> foundCustomers = customerRepository.findAll();
        LoggerFactory.getLogger(CustomerRepositoryTest.class).info("Number of customers found: " + foundCustomers.size());

        Assertions.assertFalse(foundCustomers.isEmpty(), "Customer list should not be empty");
        Assertions.assertEquals(1, foundCustomers.size(), "There should be one customer in the database");
        Assertions.assertEquals("John", foundCustomers.get(0).getFirstName(), "First name should match");
        Assertions.assertEquals("Doe", foundCustomers.get(0).getLastName(), "Last name should match");

        System.out.println("Customer retrieved successfully: " + foundCustomers.get(0).getFirstName() + " " + foundCustomers.get(0).getLastName());

    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testFindByCustomerId() {

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setEmail("jd@gmail.com");
        customer.setPhone("1234567890");
        Customer savedCustomer = customerRepository.save(customer);

        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());
        LoggerFactory.getLogger(CustomerRepositoryTest.class).info("Customer found: " + foundCustomer.get().getFirstName() + " " + foundCustomer.get().getLastName());

        Assertions.assertTrue(foundCustomer.isPresent(), "Customer should be found");
        Assertions.assertEquals("Jane", foundCustomer.get().getFirstName(), "First name should match");
        Assertions.assertEquals("Doe", foundCustomer.get().getLastName(), "Last name should match");
        System.out.println("Customer retrieved successfully: " + foundCustomer.get().getFirstName() + " " + foundCustomer.get().getLastName());

    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testDeleteCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Mark");
        customer.setLastName("Smith");
        customer.setEmail("ms@gmail.com");
        customer.setPhone("0987654321");
        Customer savedCustomer = customerRepository.save(customer);
        LoggerFactory.getLogger(CustomerRepositoryTest.class).info("Customer saved successfully: " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());

        customerRepository.deleteById(savedCustomer.getId());
        LoggerFactory.getLogger(CustomerRepositoryTest.class).info("Customer deleted successfully: " + savedCustomer.getFirstName() + " " + savedCustomer.getLastName());
        Optional<Customer> deletedCustomer = customerRepository.findById(savedCustomer.getId());
        Assertions.assertFalse(deletedCustomer.isPresent(), "Customer should be deleted");
        System.out.println("Customer with ID " + savedCustomer.getId() + " deleted successfully.");
    }

}
