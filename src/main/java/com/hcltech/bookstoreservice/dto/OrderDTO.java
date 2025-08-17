package com.hcltech.bookstoreservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private int customerId;
    @FutureOrPresent(message = "Date must be today or future")
    private LocalDate date;
    @Valid
    private List<PurchasedBookDTO> BookDetails = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(int customerId, LocalDate date, List<PurchasedBookDTO> BookDetails) {
        this.customerId = customerId;
        this.date = date;
        this.BookDetails = BookDetails;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<PurchasedBookDTO> getBookDetails() {
        return BookDetails;
    }

    public void setBookDetails(List<PurchasedBookDTO> bookDetails) {
        BookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "customerId=" + getCustomerId() +
                ", date=" + getDate() +
                ", bookDTOS=" + getBookDetails() +
                '}';
    }
}
