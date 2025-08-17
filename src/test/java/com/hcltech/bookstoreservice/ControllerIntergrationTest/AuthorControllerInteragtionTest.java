package com.hcltech.bookstoreservice.ControllerIntergrationTest;

import com.hcltech.bookstoreservice.dto.AuthorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerInteragtionTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void should_CreateAuthors() {

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("John Doe");
        authorDTO.setBiography("Bio");

        // Save the author
        AuthorDTO response = testRestTemplate.postForObject("/createAuthor", authorDTO, AuthorDTO.class);
        System.out.println("Response: " + response);

        Assertions.assertEquals("John Doe", response.getAuthorName());
        Assertions.assertEquals("Bio", response.getBiography());
        System.out.println("Author created successfully: " + response.getAuthorName());

    }


    @Test
    public void should_GetAuthorByID() {
        // First, create the author
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("John Doe");
        authorDTO.setBiography("Bio");

        AuthorDTO created = testRestTemplate.postForObject("/createAuthor", authorDTO, AuthorDTO.class);

        // Then, retrieve the author by ID (assuming ID is 1 or returned in `created`)
        AuthorDTO response = testRestTemplate.getForObject("/getAuthor?authorId=1", AuthorDTO.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("John Doe", response.getAuthorName());
        Assertions.assertEquals("Bio", response.getBiography());
        System.out.println("Author retrieved successfully: " + response.getAuthorName());
    }


    @Test
    public void should_CreateUpdateAndGetAuthorByID() {
        //Create Author
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName("John Doe");
        authorDTO.setBiography("Bio");

        AuthorDTO createdAuthor = testRestTemplate.postForObject("/createAuthor", authorDTO, AuthorDTO.class);

        Assertions.assertNotNull(createdAuthor);
        Assertions.assertEquals("John Doe", createdAuthor.getAuthorName());

        //  Update Author
        createdAuthor.setAuthorName("Jane Doe");
        createdAuthor.setBiography("Updated Bio");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthorDTO> requestEntity = new HttpEntity<>(createdAuthor, headers);

        String updateUrl = "/updateAuthor?autherId=1"; // assuming ID is available
        ResponseEntity<AuthorDTO> updateResponse = testRestTemplate.exchange(updateUrl, HttpMethod.PUT, requestEntity, AuthorDTO.class);

        Assertions.assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

        // Step 3: Retrieve Updated Author
        String getUrl = "/getAuthor?authorId=1";
        AuthorDTO updated = testRestTemplate.getForObject(getUrl, AuthorDTO.class);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Jane Doe", updated.getAuthorName());
        Assertions.assertEquals("Updated Bio", updated.getBiography());

        System.out.println("Updated Author: " + updated.getAuthorName());
        System.out.println("Author biography: " + updated.getBiography());
    }

}
