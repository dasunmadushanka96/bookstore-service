package com.hcltech.bookstoreservice.dto;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBookDTO {

    private int orderId;
    private String customerName;
    private LocalDate orderDate;
    private double billAmount;
    @Valid
    private List<PurchasedBookStDTO> BookDetails = new ArrayList<>();

    public OrderBookDTO(int orderId, String customerName, LocalDate orderDate, double billAmount, List<PurchasedBookStDTO> bookDetails) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.billAmount = billAmount;
        BookDetails = bookDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public List<PurchasedBookStDTO> getBookDetails() {
        return BookDetails;
    }

    public void setBookDetails(List<PurchasedBookStDTO> bookDetails) {
        BookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "OrderBookDTO{" +
                "orderId=" + getOrderId() +
                ", customerName='" + getCustomerName() + '\'' +
                ", orderDate=" + getOrderDate() +
                ", billAmount=" + getBillAmount() +
                ", BookDetails=" + getBookDetails() +
                '}';
    }
}
