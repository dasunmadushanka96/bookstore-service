package com.hcltech.bookstoreservice.mapper;


import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.dto.BookDTO;
import com.hcltech.bookstoreservice.model.Author;
import com.hcltech.bookstoreservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    public BookDTO bookEntityToDTO(Book book){

        BookDTO bookDTO=new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPublicationDate(book.getPublicationDate());

        Set<AuthorDTO> authorDTO = book.getAuthors().stream()
                .map(author -> authorToDTO(author)).collect(Collectors.toSet());
        bookDTO.setAuthors(authorDTO);

//        book.getAuthors().forEach(author -> author.getBooks().add(book));

        return bookDTO;

    }

    public Book bookDTOToEntity(BookDTO bookDTO){

        Book book=new Book();

        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setPublicationDate(bookDTO.getPublicationDate());

        Set<Author> authors = bookDTO.getAuthors().stream()
                .map(this::authorToEntity).collect(Collectors.toSet());

        book.setAuthors(authors);

        //linking book to author
        book.getAuthors().forEach(author -> author.getBooks().add(book));

        return book;
    }

    public Author authorDTOTOEntity(AuthorDTO authorDTO){
        Author author=new Author();
        author.setAuthorName(authorDTO.getAuthorName());
        author.setBiography(authorDTO.getBiography());

        Set<Book> books = authorDTO.getBooks().stream()
                .map(bookDTO -> bookDTOToEntity(bookDTO)).collect(Collectors.toSet());
        books.stream().map(book -> book.getAuthors().add(author)).collect(Collectors.toSet());

        author.getBooks().addAll(books);

        return author;
    }

    public AuthorDTO authorEntityToDTO(Author author){
        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setAuthorName(author.getAuthorName());
        authorDTO.setBiography(author.getBiography());
        Set<BookDTO> bookDTO = author.getBooks().stream().map(book -> bookEntityToDTO(book)).collect(Collectors.toSet());
        authorDTO.setBooks(bookDTO);
        return authorDTO;
    }

    public AuthorDTO authorToDTO(Author author) {
        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setAuthorName(author.getAuthorName());
        authorDTO.setAuthorName(author.getAuthorName());
        authorDTO.setBiography(author.getBiography());
        return authorDTO;
    }

    public Author authorToEntity(AuthorDTO authorDTO) {
        Author author=new Author();
        author.setAuthorName(authorDTO.getAuthorName());
        author.setBiography(authorDTO.getBiography());
        return author;
    }

}
