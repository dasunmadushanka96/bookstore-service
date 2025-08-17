package com.hcltech.bookstoreservice.RepositoryTest;

import com.hcltech.bookstoreservice.model.Author;
import com.hcltech.bookstoreservice.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Author author = new Author();
        //author.setAuthorId(1);
        author.setAuthorName("John Doe");
        author.setBiography("A renowned author");
        author.setBooks(null);
        authorRepository.save(author);
    }

    @Test
    public void context_laods() {
        Assertions.assertNotNull(authorRepository, "AuthorRepository should not be null");
        Assertions.assertNotNull(dataSource, "DataSource should not be null");
        Assertions.assertNotNull(entityManager, "EntityManager should not be null");
    }

    @Test
    void should_return_all_authors() {
        List<Author> results = authorRepository.findAll();
        Assertions.assertNotNull(results, "Results should not be null");
        Assertions.assertEquals(1, results.size(), "Initial size of results should be 0");
        System.out.println("Authors retrieved successfully: " + results.size());
        System.out.println("Author Name: " + results.get(0).getAuthorName() + ", Biography: " + results.get(0).getBiography());

     }

}
