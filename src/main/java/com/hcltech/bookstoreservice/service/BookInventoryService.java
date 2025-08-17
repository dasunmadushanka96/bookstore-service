package com.hcltech.bookstoreservice.service;

import com.hcltech.bookstoreservice.dto.BookInventoryDTO;
import com.hcltech.bookstoreservice.exception.BadRequestException;
import com.hcltech.bookstoreservice.mapper.InventoryMapper;
import com.hcltech.bookstoreservice.model.Book;
import com.hcltech.bookstoreservice.model.BookInventory;
import com.hcltech.bookstoreservice.repository.BookInventoryRepository;
import com.hcltech.bookstoreservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookInventoryService {

    private BookInventoryRepository inventoryRepository;
    private InventoryMapper inventoryMapper;
    private BookRepository bookRepository;

    public BookInventoryService(BookInventoryRepository inventoryRepository, InventoryMapper inventoryMapper, BookRepository bookRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookInventoryDTO> getAll() {
        return inventoryRepository.findAll().stream().map(inventory -> inventoryMapper.toDto(inventory)).collect(Collectors.toList());
    }

    public BookInventoryDTO getOneById(int id) {
        BookInventory bookInventory = inventoryRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Cannot find inventory with id "+id));
        return inventoryMapper.toDto(bookInventory);
    }

    public BookInventoryDTO create(BookInventoryDTO inventoryDTO) {
        Book book =  bookRepository.findById(inventoryDTO.getBookId())
                .orElseThrow(()-> new BadRequestException("Cannot find book with id "+inventoryDTO.getBookId()));

        BookInventory bookInventory = inventoryMapper.toEntity(inventoryDTO);
        bookInventory.setBook(book);

        BookInventory inventoryByBookId = getOneByBookId(inventoryDTO.getBookId());
        if(inventoryByBookId != null){
            inventoryDTO.setId(inventoryByBookId.getId());
            return update(inventoryDTO);
        }
        return inventoryMapper.toDto(inventoryRepository.save(bookInventory));
    }

    public BookInventoryDTO update(BookInventoryDTO inventoryDTO) {
        if(!inventoryRepository.existsById(inventoryDTO.getId())){
            throw new BadRequestException("Cannot find entry with id "+ inventoryDTO.getId());
        }
        Book book =  bookRepository.findById(inventoryDTO.getBookId())
                .orElseThrow(()-> new RuntimeException("Cannot find book with id "+inventoryDTO.getBookId()));

        BookInventory bookInventory = inventoryMapper.toEntity(inventoryDTO);
        bookInventory.setBook(book);

        BookInventory savedInventory = inventoryRepository.save(bookInventory);
        return inventoryMapper.toDto(savedInventory);
    }

    public void delete(int id) {
        if(!inventoryRepository.existsById(id)){
            throw new BadRequestException("Cannot find entry with id "+id);
        }
        inventoryRepository.deleteById(id);
    }

    public BookInventory getOneByBookId(Long id) {
        return inventoryRepository.findInventoryByBookId(id);
    }

}
