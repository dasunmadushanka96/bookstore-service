package com.hcltech.bookstoreservice.dto;

import com.hcltech.bookstoreservice.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class AuthorDTO {
    @NotBlank(message = "Author name is required")
    private String authorName;
    @Size(max = 500, message = "Biography must be at most 500 characters")
    private String biography;
    Set<BookDTO> books=new HashSet<>();

    public AuthorDTO() {
    }

    public AuthorDTO(String authorName, LocalDate releaseDate, String biography, Set<BookDTO> books) {
        this.authorName = authorName;
        this.biography = biography;
        this.books = books;
    }

    public AuthorDTO(Author author) {
        this.authorName=author.getAuthorName();
        this.biography=author.getBiography();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "authorName='" + authorName + '\'' +
                ", biography='" + biography + '\'' +
                ", books=" + books +
                '}';
    }
}
