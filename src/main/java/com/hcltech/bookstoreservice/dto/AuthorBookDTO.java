package com.hcltech.bookstoreservice.dto;

import com.hcltech.bookstoreservice.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorBookDTO {

    private AuthorDTO authorDTO;
    //// Change this to a set
    private List<BookDTO> books = new ArrayList<>();

    public AuthorBookDTO() {
    }

    public AuthorBookDTO(AuthorDTO authorDTO, List<BookDTO> bookIds) {
        this.authorDTO = authorDTO;
        this.books = bookIds;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorBookDTO{" +
                "authorDTO=" + getAuthorDTO() +
                //", bookIds=" + getBooks() +
                '}';
    }
}
