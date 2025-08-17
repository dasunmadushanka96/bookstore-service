package com.hcltech.bookstoreservice.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookInventoryDTO {

    private int id;
    @NotNull(message = "BookId is required")
    private Long bookId;
    @Min(value = 1, message = "Value must be positive")
    private int quantity;
    @FutureOrPresent(message = "Date must be today or future")
    private LocalDate date;

    public BookInventoryDTO() {
    }

    public BookInventoryDTO(int id, Long bookId, int quantity, LocalDate date) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookInventoryDTO{" +
                "id=" + getId() +
                ", bookId=" + getBookId() +
                ", quantity=" + getQuantity() +
                ", date=" + getDate() +
                '}';
    }
}
