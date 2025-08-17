package com.hcltech.bookstoreservice.service;


import com.hcltech.bookstoreservice.dto.BookDTO;
import com.hcltech.bookstoreservice.mapper.MapperUtil;
import com.hcltech.bookstoreservice.model.Author;
import com.hcltech.bookstoreservice.model.Book;
import com.hcltech.bookstoreservice.repository.AuthorRepository;
import com.hcltech.bookstoreservice.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final MapperUtil mapperUtil;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorService autherService,
                       MapperUtil mapperUtil, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorService =autherService;
        this.mapperUtil=mapperUtil;
        this.authorRepository=authorRepository;
    }


    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = mapperUtil.bookDTOToEntity(bookDTO);
        Book savedBook=bookRepository.save(book);
        return mapperUtil.bookEntityToDTO(savedBook);
    }


    public BookDTO getOneBookById(Long id) {
        return mapperUtil.bookEntityToDTO(bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")));
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(book -> mapperUtil.bookEntityToDTO(book))
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(Long bookId,Long authorId,BookDTO bookDTO) throws Exception {
        Book existingBookToUpdate=bookRepository.findById(bookId).orElseThrow(()->
                new EntityNotFoundException("Book having id "+bookId+" to update not present in table"));
        existingBookToUpdate.setIsbn(bookDTO.getIsbn());
        existingBookToUpdate.setTitle(bookDTO.getTitle());
        existingBookToUpdate.setPublicationDate(bookDTO.getPublicationDate());
        existingBookToUpdate.setPrice(bookDTO.getPrice());

        //attach new non-present auther to existing book
        if(bookDTO.getAuthors()!=null && !bookDTO.getAuthors().isEmpty()){
            Set<Author> newAuthorsToAdd=bookDTO.getAuthors().stream().map(authorDTO ->
            {
                Author newAuthor=mapperUtil.authorDTOTOEntity(authorDTO);
                newAuthor.getBooks().add(existingBookToUpdate);
                return newAuthor;
            }
            ).collect(Collectors.toSet());
            existingBookToUpdate.getAuthors().addAll(newAuthorsToAdd);
        }

        //attach auther id to existing book
        if(authorId!=null){
            Author autherToAttach=authorRepository.findById(authorId).orElseThrow(()->
                    new EntityNotFoundException("Author having id "+authorId+" to attach not present in Author table"));

            existingBookToUpdate.getAuthors().add(autherToAttach);
            autherToAttach.getBooks().add(existingBookToUpdate);
        }
        Book updatedBook=bookRepository.save(existingBookToUpdate);
        return mapperUtil.bookEntityToDTO(updatedBook);
    }

    public Boolean deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book to delete with id "+bookId+" not found"));
        // Unlink from authors
        for (Author author : book.getAuthors()) {
            author.getBooks().remove(book);
        }
        book.getAuthors().clear();
        bookRepository.delete(book);
        return true;
    }

    public Book getOneById(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

}
