package com.hcltech.bookstoreservice.ControllerTest;

import com.hcltech.bookstoreservice.controller.AuthorController;
import com.hcltech.bookstoreservice.controller.BookController;
import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.dto.BookDTO;
import com.hcltech.bookstoreservice.service.AuthorService;
import com.hcltech.bookstoreservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AuthorController.class, BookController.class})
public class AllControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @MockitoBean
    private BookService bookService;

    @Test
    void shoud_GetAuthors() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        when(authorService.getAuthor(1L)).thenReturn(authorDTO);

        mockMvc.perform(get("/getAuthor").param("authorId", "1"))
                .andExpect(status().isOk())
                .andDo(print());
        System.out.println("Author retrieved successfully.");
    }


    @Test
    void should_GetAllBooks() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("ISBN-1234567890");
        bookDTO.setTitle("Test Book");
        bookDTO.setPublicationDate(LocalDate.of(2025, 5, 10));

          when(bookService.getOneBookById(1L)).thenReturn(bookDTO);

        mockMvc.perform(get("/getOneBook").param("bookId", "1"))
                .andExpect(status().isOk())
                .andDo(print());
        System.out.println(""+ bookDTO.getIsbn() + " " + bookDTO.getTitle() + " " + bookDTO.getPublicationDate());
        System.out.println("" + bookDTO.getTitle() + " retrieved successfully.");
    }

}