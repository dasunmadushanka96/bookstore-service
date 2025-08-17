package com.hcltech.bookstoreservice.model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchasedBook {

    @EmbeddedId
    private PurchasedBookPK purchasedBookPK;
    private int quantity;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    public PurchasedBook() {
    }

    public PurchasedBook(PurchasedBookPK purchasedBookPK, int quantity, double unitPrice) {
        this.purchasedBookPK = purchasedBookPK;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public PurchasedBook(int orderId, Long bookId, int quantity, double unitPrice) {
        this.purchasedBookPK = new PurchasedBookPK(orderId, bookId);
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public PurchasedBookPK getPurchasedBookPK() {
        return purchasedBookPK;
    }

    public void setPurchasedBookPK(PurchasedBookPK purchasedBookPK) {
        this.purchasedBookPK = purchasedBookPK;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "PurchasedBook{" +
                "purchasedBookPK=" + getPurchasedBookPK() +
                ", quantity=" + getQuantity() +
                ", unitPrice=" + getUnitPrice() +
                ", book=" + getBook() +
                '}';
    }
}
