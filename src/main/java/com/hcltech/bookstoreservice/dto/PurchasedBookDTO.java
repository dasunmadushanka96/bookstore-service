package com.hcltech.bookstoreservice.dto;

import jakarta.validation.constraints.Min;

public class PurchasedBookDTO {

    private Long bookId;
    @Min(value = 1, message = "Value must be positive")
    private int quantity;
    //private double unitPrice;

    public PurchasedBookDTO() {
    }

    public PurchasedBookDTO(Long bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    //    public PurchasedBookDTO(Long bookId, int quantity, double unitPrice) {
//        this.bookId = bookId;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//    }

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

//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }



//    @Override
//    public String toString() {
//        return "PurchasedBookDTO{" +
//                "bookId=" + getBookId() +
//                ", quantity=" + getQuantity() +
//                ", unitPrice=" + getUnitPrice() +
//                '}';
//    }

    @Override
    public String toString() {
        return "PurchasedBookDTO{" +
                "bookId=" + getBookId() +
                ", quantity=" + getQuantity() +
                '}';
    }
}
