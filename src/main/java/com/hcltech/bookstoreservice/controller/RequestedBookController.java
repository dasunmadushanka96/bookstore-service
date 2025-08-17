package com.hcltech.bookstoreservice.controller;

import com.hcltech.bookstoreservice.dto.RequestedBookDTO;
import com.hcltech.bookstoreservice.service.RequestedBookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requestedBooks")
public class RequestedBookController {

    private RequestedBookService requestedBookService;

    public RequestedBookController(RequestedBookService requestedBookService) {
        this.requestedBookService = requestedBookService;
    }

    @PostMapping
    public RequestedBookDTO createRequestedBook(@RequestBody RequestedBookDTO requestedBookDTO) {
        return requestedBookService.createRequestedBook(requestedBookDTO);
    }

    @GetMapping("/{id}")
    public RequestedBookDTO getRequestedBookById(@PathVariable Long id) {
        return requestedBookService.getRequestedBookById(id);
    }

    @GetMapping("/all")
    public Iterable<RequestedBookDTO> getAllRequestedBooks() {
        return requestedBookService.getAllRequestedBooks();
    }

    @PutMapping("/{id}")
    public RequestedBookDTO updateRequestedBook(@PathVariable Long id, @RequestBody RequestedBookDTO requestedBookDTO) {
        return requestedBookService.updateRequestedBook(id, requestedBookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestedBook(@PathVariable Long id) {
        requestedBookService.deleteRequestedBook(id);
    }

}
