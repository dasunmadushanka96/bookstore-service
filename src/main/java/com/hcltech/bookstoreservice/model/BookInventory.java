package com.hcltech.bookstoreservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 50)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id", unique = true)
    private Book book;

    private LocalDate date;

    public BookInventory() {
    }

    public BookInventory(int id, int quantity, Book book, LocalDate date) {
        this.id = id;
        this.quantity = quantity;
        this.book = book;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookInventory{" +
                "id=" + getId() +
                ", quantity=" + getQuantity() +
                ", book=" + getBook() +
                ", date=" + getDate() +
                '}';
    }
}
