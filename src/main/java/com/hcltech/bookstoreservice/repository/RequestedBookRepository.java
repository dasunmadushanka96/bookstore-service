package com.hcltech.bookstoreservice.repository;

import com.hcltech.bookstoreservice.model.RequestedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestedBookRepository extends JpaRepository<RequestedBook, Long> {

}
