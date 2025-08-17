package com.hcltech.bookstoreservice.dto;

import java.util.HashSet;
import java.util.Set;

public class CustomerBookDTO{

    private CustomerDTO customerDTO;
    private Set<String> purchasedBooks = new HashSet<>();

    public CustomerBookDTO() {
    }

    public CustomerBookDTO(CustomerDTO customerDTO, Set<String> purchasedBooks) {
        this.customerDTO = customerDTO;
        this.purchasedBooks = purchasedBooks;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Set<String> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(Set<String> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    @Override
    public String toString() {
        return "CustomerBookDTO{" +
                "customerDTO=" + getCustomerDTO() +
                ", purchasedBooks=" + getPurchasedBooks() +
                '}';
    }
}
