package com.hcltech.bookstoreservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hcltech.bookstoreservice.dto.AuthorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @NotBlank(message = "Author name is required")
    @Size(max = 255, message = "Author name must be at most 255 characters")
    private String authorName;


    @Size(max = 1000, message = "Biography must be at most 1000 characters")
    private String biography;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonBackReference(value = "author-book")
    private Set<Book> books=new HashSet<>();

    public Author() {
    }

    public Author(Long authorId, String authorName, String biography, Set<Book> books) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.biography = biography;
        this.books = books;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}
