package com.hcltech.bookstoreservice.RepositoryTest;

import com.hcltech.bookstoreservice.model.Book;
import com.hcltech.bookstoreservice.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Save and Find Book by Name")
    void testSaveAndFindBookByName()  {

        Book book = new Book();
        book.setTitle("Spring Boot in Action");
        book.setPrice(20.22);
        bookRepository.save(book);

        List<Book> foundBooks = bookRepository.findAll();
        if (foundBooks.isEmpty()) {
            bookRepository.save(book);
            System.out.println("Book saved successfully: " + book.getTitle());
        } else {
            System.out.println("Book already exists: " + foundBooks.get(0).getTitle());
        }

        Assertions.assertFalse(foundBooks.isEmpty(), "Book list should not be empty");

    }


}
