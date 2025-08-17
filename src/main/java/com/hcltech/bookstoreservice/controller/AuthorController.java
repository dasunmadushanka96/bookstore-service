package com.hcltech.bookstoreservice.controller;

import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/createAuthor")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.createAuthor(authorDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAuthor")
    public ResponseEntity<AuthorDTO> getAuthor(@RequestParam Long authorId) throws Exception {
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAuthor")
    public ResponseEntity<List<AuthorDTO>> getAllAuthor() {
        return new ResponseEntity<>(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @PutMapping(value = "/updateAuthor")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestParam Long autherId,
                                                  @RequestParam(required = false) Long bookIdToLink,
                                                  @RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.updateAuthor(autherId,bookIdToLink,authorDTO),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAuthor")
    public Boolean deleteAuthor(@RequestParam Long authorId){
        return  authorService.deleteAuthor(authorId);
    }

}
