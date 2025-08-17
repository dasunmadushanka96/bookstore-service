package com.hcltech.bookstoreservice.ControllerTest;

import com.hcltech.bookstoreservice.controller.AuthorController;
import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void testGetAuthor() throws Exception {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("John Doe");
        authorDTO.setBiography("Bio");
       // authorDTO.setReleaseDate(LocalDate.of(2023, 10, 1));
        // Simulate the service layer returning the authorDTO
        when(authorService.getAuthor(1L)).thenReturn(authorDTO);

        mockMvc.perform(get("/getAuthor")
                        .param("authorId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorName").value("John Doe"))
                .andExpect(jsonPath("$.biography").value("Bio"));
           //     .andExpect(jsonPath("$.releaseDate").value("2023-10-01"));
        System.out.println("Author retrieved successfully.");
    }
}