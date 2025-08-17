package com.hcltech.bookstoreservice.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PurchasedBookPK {

    private int orderId;
    private Long bookId;

    public PurchasedBookPK() {
    }

    public PurchasedBookPK(int orderId, Long bookId) {
        this.orderId = orderId;
        this.bookId = bookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "PurchasedBookPK{" +
                "orderId=" + getOrderId() +
                ", bookId=" + getBookId() +
                '}';
    }
}
