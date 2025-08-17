package com.hcltech.bookstoreservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`Order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int customerId;

    private LocalDate date;

    //Bidirectional
    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchasedBook> purchasedBookList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    public Order() {
    }

    public Order(int customerId, LocalDate date) {
        this.customerId = customerId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<PurchasedBook> getPurchasedBookList() {
        return purchasedBookList;
    }

    public void setPurchasedBookList(List<PurchasedBook> purchasedBookList) {
        this.purchasedBookList = purchasedBookList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + getCustomer() +
                ", purchasedBookList=" + getPurchasedBookList() +
                ", date=" + getDate() +
                ", customerId=" + getCustomerId() +
                ", id=" + getId() +
                '}';
    }
}
