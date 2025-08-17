package com.hcltech.bookstoreservice.controller;


import com.hcltech.bookstoreservice.dto.BookDTO;
import com.hcltech.bookstoreservice.service.AuthorService;
import com.hcltech.bookstoreservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @PostMapping(value = "/saveBook")
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO){
        return new ResponseEntity<>(bookService.saveBook(bookDTO),HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping(value = "/getOneBook")
    public ResponseEntity<BookDTO> getOneBook(@RequestParam Long bookId){
        return new ResponseEntity<>(bookService.getOneBookById(bookId),HttpStatus.OK);
    }

    @PutMapping(value = "/updateBook")
    public ResponseEntity<BookDTO> updateBook(@RequestParam Long bookId,@RequestParam(required = false) Long authorIdToLink,
                           @RequestBody BookDTO bookDTO) throws Exception {
        return new ResponseEntity<>(bookService.updateBook(bookId,authorIdToLink,bookDTO),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBook")
    public ResponseEntity<Boolean> deleteBook(@RequestParam Long bookId){
        return new ResponseEntity<>(bookService.deleteBook(bookId),HttpStatus.OK);
    }

}
