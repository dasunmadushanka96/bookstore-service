package com.hcltech.bookstoreservice.service;

import com.hcltech.bookstoreservice.dto.*;
import com.hcltech.bookstoreservice.exception.BadRequestException;
import com.hcltech.bookstoreservice.model.*;
import com.hcltech.bookstoreservice.repository.BookInventoryRepository;
import com.hcltech.bookstoreservice.repository.OrderRepository;
import com.hcltech.bookstoreservice.repository.PurchasedBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasedBookService {

    Logger LOGGER = LoggerFactory.getLogger(PurchasedBookService.class);

    private PurchasedBookRepository purchasedBookRepository;
    private OrderRepository orderRepository;
    private CustomerService customerService;
    private BookService bookService;
    private BookInventoryService inventoryService;
    private BookInventoryRepository inventoryRepository;

    public PurchasedBookService(PurchasedBookRepository purchasedBookRepository, OrderRepository orderRepository, CustomerService customerService, BookService bookService, BookInventoryService inventoryService, BookInventoryRepository inventoryRepository) {
        this.purchasedBookRepository = purchasedBookRepository;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.bookService = bookService;
        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public OrderDTO create(OrderDTO orderDTO){

        LOGGER.info("Placing order with customer id: "+ orderDTO.getCustomerId());

        List<Long> nonExistingBooks = new ArrayList<>();
        List<Long> nonExistingInventories = new ArrayList<>();
        List<Long> notEnoughInventories = new ArrayList<>();

        if(customerService.getOneById(orderDTO.getCustomerId()) == null){
            throw new BadRequestException(" Customer does not exists");
        }

        for (PurchasedBookDTO bookDTO : orderDTO.getBookDetails()) {
            if (bookService.getOneById(bookDTO.getBookId()) == null){
                nonExistingBooks.add(bookDTO.getBookId());
            }
            BookInventory inventoryByBookId = inventoryRepository.findInventoryByBookId(bookDTO.getBookId());
            if(inventoryByBookId == null){
                nonExistingInventories.add(bookDTO.getBookId());
            }else{
                if(inventoryByBookId.getQuantity() < bookDTO.getQuantity() || inventoryByBookId.getQuantity() == 0){
                    notEnoughInventories.add(bookDTO.getBookId());
                }
            }
        }

        if(nonExistingBooks.isEmpty() && nonExistingInventories.isEmpty() && notEnoughInventories.isEmpty()){

            LOGGER.info("Saving order with customer id: "+ orderDTO.getCustomerId());

            Order savedOrder = orderRepository.save(new Order(orderDTO.getCustomerId(), orderDTO.getDate()));
            for (PurchasedBookDTO bookDetail : orderDTO.getBookDetails()) {
                Book book = bookService.getOneById(bookDetail.getBookId());
                purchasedBookRepository.save(new PurchasedBook(savedOrder.getId(), bookDetail.getBookId(), bookDetail.getQuantity(), book.getPrice()));
                BookInventory bookInventory = inventoryService.getOneByBookId(bookDetail.getBookId());
                bookInventory.setQuantity(bookInventory.getQuantity() - bookDetail.getQuantity());
                inventoryRepository.save(bookInventory);
            }
            return orderDTO;

        }else{
            if(!nonExistingBooks.isEmpty()){
                String books = nonExistingBooks.toString();
                throw new BadRequestException(" Books "+ books+ " not exists");
            } else if (! nonExistingInventories.isEmpty()) {
                String inventories = nonExistingInventories.toString();
                throw new BadRequestException(" Inventories "+ inventories+ " not exists");
            } else {
                String inventories = notEnoughInventories.toString();
                throw new BadRequestException(" Inventories are not enough for "+ inventories+ " book Ids");
            }

        }
    }

    public List<OrderBookDTO> getAll() {

        LOGGER.info("Retrieving details of all orders: ");

        return orderRepository.findAll().stream()
                .map(order -> new OrderBookDTO(order.getId(), getFullName(order.getCustomer()), order.getDate(),
                        calculateBill(order.getPurchasedBookList()), order.getPurchasedBookList().stream()
                        .map(book -> new PurchasedBookStDTO(book.getBook().getTitle(), book.getQuantity(),
                                book.getUnitPrice())).collect(Collectors.toList()))).collect(Collectors.toList());

    }

    private double calculateBill(List<PurchasedBook> purchasedBookList) {
        double billAmount = 0;
        if(purchasedBookList ==  null){
            return billAmount;
        }
        for (PurchasedBook purchasedBook : purchasedBookList) {
            billAmount += purchasedBook.getUnitPrice() * purchasedBook.getQuantity();
        }
        return billAmount;
    }


    private String getFullName(Customer customer) {
        String fullName = "";
        if(customer != null){
            fullName = customer.getFirstName() + " "+customer.getLastName();
        }
        return fullName;
    }
}
