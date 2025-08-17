package com.hcltech.bookstoreservice.controller;

import com.hcltech.bookstoreservice.dto.BookInventoryDTO;
import com.hcltech.bookstoreservice.service.BookInventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore-service/v1/inventory")
public class BookInventoryController {

    private BookInventoryService inventoryService;

    public BookInventoryController(BookInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    private ResponseEntity<List<BookInventoryDTO>> getAll(){
        return new ResponseEntity<List<BookInventoryDTO>>(inventoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<BookInventoryDTO> getOneById(@PathVariable int id){
        return new ResponseEntity<BookInventoryDTO>(inventoryService.getOneById(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<BookInventoryDTO> create(@Valid @RequestBody BookInventoryDTO inventoryDTO){
        return new ResponseEntity<BookInventoryDTO>(inventoryService.create(inventoryDTO), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<BookInventoryDTO> update(@Valid @RequestBody BookInventoryDTO inventoryDTO){
        return new ResponseEntity<BookInventoryDTO>(inventoryService.update(inventoryDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable int id){
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
