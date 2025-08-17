package com.hcltech.bookstoreservice.service;


import com.hcltech.bookstoreservice.dto.AuthorDTO;
import com.hcltech.bookstoreservice.mapper.MapperUtil;
import com.hcltech.bookstoreservice.model.Author;
import com.hcltech.bookstoreservice.model.Book;
import com.hcltech.bookstoreservice.repository.AuthorRepository;
import com.hcltech.bookstoreservice.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    MapperUtil mapperUtil;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author=mapperUtil.authorDTOTOEntity(authorDTO);
        return mapperUtil.authorEntityToDTO(authorRepository.save(author));
    }

    public AuthorDTO getAuthor(Long authorId) throws Exception {
        return mapperUtil.authorEntityToDTO(authorRepository.findById(authorId)
                .orElseThrow(()->new Exception("user not found")));
    }

    public List<AuthorDTO> getAllAuthor() {
        return authorRepository.findAll().stream()
                .map(author -> mapperUtil.authorEntityToDTO(author)).collect(Collectors.toList());
    }

    public AuthorDTO updateAuthor(Long authorId,Long bookId,AuthorDTO authorDTO){
        Author autherToUpdate=authorRepository.findById(authorId)
                .orElseThrow(()->new EntityNotFoundException("Autoher with id "
                        +authorId+" not present in table to update"));
        autherToUpdate.setAuthorName(authorDTO.getAuthorName());
        autherToUpdate.setBiography(authorDTO.getBiography());

        // link new book while update
        if(authorDTO.getBooks()!=null && !authorDTO.getBooks().isEmpty()){
            Set<Book> newBooksToAdd=authorDTO.getBooks().stream().map(bookDTO -> {
                Book book=mapperUtil.bookDTOToEntity(bookDTO);
                book.getAuthors().add(autherToUpdate);
                return book;
            }).collect(Collectors.toSet());
            autherToUpdate.getBooks().addAll(newBooksToAdd);
        }

        if(bookId!=null){
            Book bookToLink = bookRepository.findById(bookId).orElseThrow(
                    () -> new EntityNotFoundException("Book having id " + bookId +
                    " to link with author not present in the table"));
            autherToUpdate.getBooks().add(bookToLink);
            bookToLink.getAuthors().add(autherToUpdate);
        }

        Author updatedAuthor=authorRepository.save(autherToUpdate);
        return mapperUtil.authorEntityToDTO(updatedAuthor);

    }

    public Boolean deleteAuthor(Long authorId){
        if(authorRepository.existsById(authorId)){
            authorRepository.deleteById(authorId);
            return true;
        }
        throw new EntityNotFoundException("Author to delete not present in table "+authorId);
    }

}
