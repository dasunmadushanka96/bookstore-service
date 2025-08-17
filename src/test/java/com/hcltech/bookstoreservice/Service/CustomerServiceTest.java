//package com.hcltech.bookstoreservice.Service;
//
//import com.hcltech.bookstoreservice.dto.AuthorDTO;
//import com.hcltech.bookstoreservice.dto.CustomerDTO;
//import com.hcltech.bookstoreservice.mapper.CustomerMapper;
//import com.hcltech.bookstoreservice.model.Author;
//import com.hcltech.bookstoreservice.model.Customer;
//import com.hcltech.bookstoreservice.repository.CustomerRepository;
//import com.hcltech.bookstoreservice.service.CustomerService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.lang.reflect.Array;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CustomerServiceTest {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private CustomerMapper customerMapper;
//
//    @InjectMocks
//    private CustomerService customerService;
//
//    private Customer customer;
//    private CustomerDTO customerDTO;
//
//    @BeforeEach
//    void setUp() {
//        customer = new Customer();
//        customer.setFirstName("John");
//        customer.setLastName("Doe");
//        customer.setEmail("jd@gmail.com");
//        customer.setPhone("123456789");
//        customer.setOrders(null);
//
//
//        customerDTO = new CustomerDTO();
//        customerDTO.setFirstName("John");
//        customerDTO.setLastName("Doe");
//        customerDTO.setEmail("jd@gmail.com");
//        customerDTO.setPhone("123456789");
//    }
//
//
//    public Customer getOneById(int id){
//        Optional<Customer> customerdetails = customerRepository.findById(id);
//        return customerdetails.orElse(null);
//    }
//
//
//    @Test
//    void testGetOneById() {
//        int customerId = 1;
//        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
//
//        Customer result = customerService.getOneById(customerId);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("John", result.getFirstName());
//    }
//
//
//}
