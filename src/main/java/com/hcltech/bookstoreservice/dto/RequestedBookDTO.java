package com.hcltech.bookstoreservice.dto;

import com.hcltech.bookstoreservice.model.RequestedBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RequestedBookDTO {

    private Long id;
    @NotBlank(message = "Book name cannot be blank")
    @Size(max = 100, message = "Book name cannot exceed 100 characters")
    private String bookName;
    @NotBlank(message = "Author name cannot be blank")
    private String authorName;
    @NotBlank(message = "Customer name cannot be blank")
    private String CustomerName;
    private LocalDate requestDate;
    @NotBlank(message = "Request status cannot be blank")
    private RequestedBook.RequestStatus requestStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public RequestedBook.RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestedBook.RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
