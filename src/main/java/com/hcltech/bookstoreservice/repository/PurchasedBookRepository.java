package com.hcltech.bookstoreservice.repository;


import com.hcltech.bookstoreservice.model.PurchasedBook;
import com.hcltech.bookstoreservice.model.PurchasedBookPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedBookRepository extends JpaRepository<PurchasedBook, PurchasedBookPK> {
}
