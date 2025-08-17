package com.hcltech.bookstoreservice.Service;

import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.dto.BookDTO;
import com.hcltech.bookstoreservice.model.Author;
import com.hcltech.bookstoreservice.model.Book;
import com.hcltech.bookstoreservice.mapper.MapperUtil;
import com.hcltech.bookstoreservice.repository.BookRepository;
import com.hcltech.bookstoreservice.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class BookServicesTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    private MapperUtil mapperUtil;

    @InjectMocks
    BookService bookService;

    private BookDTO bookDTO;
    private Book book;

    @BeforeEach
    void setUp(){

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("Dasun");
        authorDTO.setBiography("Sri Lankan author");

        AuthorDTO authorDTO2 = new AuthorDTO();
        authorDTO2.setAuthorName("Kasun");
        authorDTO2.setBiography("Sri Lankan author");

        Set<AuthorDTO> authorDTOS = new HashSet<>();
        authorDTOS.add(authorDTO);
        authorDTOS.add(authorDTO2);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Spring-BOOT");
        bookDTO.setIsbn("ISBN-231");
        bookDTO.setPublicationDate(LocalDate.of(2025, 5, 10));
        bookDTO.setAuthors(authorDTOS);

        // Created Author entities
        Author author = new Author();
        author.setAuthorName("Dasun");
        author.setBiography("Sri Lankan author");

        Author author2 = new Author();
        author2.setAuthorName("Kasun");
        author2.setBiography("Sri Lankan author");

        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author);
        authorSet.add(author2);

        // Initialize Book entity
        book = new Book();
        book.setId(1L);
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setAuthors(authorSet);


    }
    //AAA - Arrange, Act, Assert
    @Test
    void createBookshouldCreateBookSuccessfully(){

        //Arrange
        Mockito.when(mapperUtil.bookDTOToEntity(Mockito.any(BookDTO.class))).thenReturn(book);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        //Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(book);
        Mockito.when(mapperUtil.bookEntityToDTO(Mockito.any(Book.class))).thenReturn(bookDTO);

        //ACT
        BookDTO createdBook = bookService.saveBook(bookDTO);
        System.out.println("This is my First Book Service Test: " + createdBook.getTitle() + " " + createdBook.getIsbn() + " " + createdBook.getPublicationDate());
        //System.out.println("This is my First Test");

        //test assert
        Assertions.assertNotNull(createdBook);
        Assertions.assertEquals(book.getIsbn(), createdBook.getIsbn());
        Assertions.assertEquals(book.getTitle(), createdBook.getTitle());
        // check if authors are equal
        Assertions.assertEquals(book.getAuthors().size(), createdBook.getAuthors().size());
        Assertions.assertTrue(book.getId() == 1);
        Assertions.assertEquals(book.getPublicationDate(), createdBook.getPublicationDate());
        System.out.println("Book creation test : passed.");
        System.out.println("Values: " + createdBook);


    }
}
