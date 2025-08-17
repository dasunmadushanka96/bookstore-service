package com.hcltech.bookstoreservice.controller;

import com.hcltech.bookstoreservice.dto.OrderBookDTO;
import com.hcltech.bookstoreservice.dto.OrderDTO;
import com.hcltech.bookstoreservice.dto.PurchasedBookDTO;
import com.hcltech.bookstoreservice.service.PurchasedBookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore-service/v1/purchase")
public class PurchaseBookController {

    Logger LOGGER = LoggerFactory.getLogger(PurchaseBookController.class);

    private PurchasedBookService purchasedBookService;

    public PurchaseBookController(PurchasedBookService purchasedBookService) {
        this.purchasedBookService = purchasedBookService;
    }

    @PostMapping
    public OrderDTO create(@Valid @RequestBody OrderDTO orderDTO){
        LOGGER.info("Placing an order with customer id: "+orderDTO.getCustomerId());
        return purchasedBookService.create(orderDTO);
    }

    @GetMapping
    public List<OrderBookDTO> get(){
        LOGGER.info("List all orders");
        return purchasedBookService.getAll();
    }

}
