package com.hcltech.bookstoreservice.repository;

import com.hcltech.bookstoreservice.model.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInventoryRepository extends JpaRepository<BookInventory, Integer> {

    @Query(value = "SELECT * FROM BOOK_INVENTORY WHERE BOOK_ID = ?1", nativeQuery = true)
    BookInventory findInventoryByBookId(Long bookId);

}
