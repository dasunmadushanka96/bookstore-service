package com.hcltech.bookstoreservice.dto;

import jakarta.validation.constraints.Min;

public class PurchasedBookStDTO {

    private String bookName;
    @Min(value = 1, message = "Value must be positive")
    private int quantity;
    private double unitPrice;

    public PurchasedBookStDTO(String bookName, int quantity, double unitPrice) {
        this.bookName = bookName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "PurchasedBookStDTO{" +
                "bookId=" + getBookName() +
                ", quantity=" + getQuantity() +
                ", unitPrice=" + getUnitPrice() +
                '}';
    }
}
