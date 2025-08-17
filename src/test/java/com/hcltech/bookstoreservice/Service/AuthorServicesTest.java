package com.hcltech.bookstoreservice.Service;

import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.model.Author;
import com.hcltech.bookstoreservice.mapper.MapperUtil;
import com.hcltech.bookstoreservice.repository.AuthorRepository;
import com.hcltech.bookstoreservice.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorServicesTest {

    @Mock
    AuthorRepository authorRepository;

    @Mock
    private MapperUtil mapperUtil;

    @InjectMocks
    AuthorService authorService;

    @Test
    void createAuthorshouldCreateAuthorSuccessfully(){

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("Dasun");
        authorDTO.setBiography("Test");
        authorDTO.setBooks(null);

        Author author = new Author();
        author.setAuthorName("Dasun");
        author.setBiography("Test");
        author.setBooks(null);
        author.setAuthorId(1L);

        Mockito.when(mapperUtil.authorDTOTOEntity(Mockito.any(AuthorDTO.class))).thenReturn(author);
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        Mockito.when(mapperUtil.authorEntityToDTO(Mockito.any(Author.class))).thenReturn(authorDTO);


        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);

        //Test assert
        Assertions.assertNotNull(createdAuthor);
        Assertions.assertEquals(author.getAuthorName(), createdAuthor.getAuthorName());
        Assertions.assertEquals(author.getBiography(), createdAuthor.getBiography());
        Assertions.assertTrue(author.getAuthorId()==1);
        System.out.println("This is my First Test: Passed.");
        System.out.println("Values: "+createdAuthor);

    }
}
