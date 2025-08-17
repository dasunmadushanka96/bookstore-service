package com.hcltech.bookstoreservice.Service;

import com.hcltech.bookstoreservice.dto.RequestedBookDTO;
import com.hcltech.bookstoreservice.model.RequestedBook;
import com.hcltech.bookstoreservice.mapper.*;
import com.hcltech.bookstoreservice.repository.RequestedBookRepository;
import com.hcltech.bookstoreservice.service.RequestedBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RequestedBookServiceTest {

    @Mock
    private RequestedBookRepository requestedBookRepository;

    @Mock
    private RequestedBookMapper requestedBookMapper;

    @InjectMocks
    private RequestedBookService requestedBookService;

    private RequestedBookDTO dto;
    private RequestedBook entity;

    @BeforeEach
    void setUp() {

        dto = new RequestedBookDTO();
        dto.setId(1L);
        dto.setBookName("SpringBoot");
        dto.setAuthorName("Kasun");
        dto.setCustomerName("Dasun");
        dto.setRequestStatus(RequestedBook.RequestStatus.PENDING);
        dto.setRequestDate(LocalDate.now());

        entity = new RequestedBook();
        entity.setId(1L);
        entity.setBookName("SpringBoot");
        entity.setAuthorName("Kasun");
        entity.setCustomerName("Dasun");
        entity.setRequestStatus(RequestedBook.RequestStatus.PENDING);
        entity.setRequestDate(LocalDate.now());
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testCreateRequestedBook() {
        when(requestedBookMapper.toEntity(dto)).thenReturn(entity);
        when(requestedBookRepository.save(any(RequestedBook.class))).thenReturn(entity);
        when(requestedBookMapper.toDTO(entity)).thenReturn(dto);

        RequestedBookDTO result = requestedBookService.createRequestedBook(dto);

        assertNotNull(result);
        assertEquals("SpringBoot", result.getBookName());
        verify(requestedBookRepository, times(1)).save(any(RequestedBook.class));
        System.out.println("Requested Book Created: " + result.getBookName());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testGetRequestedBookById() {
        when(requestedBookRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(requestedBookMapper.toDTO(entity)).thenReturn(dto);

        RequestedBookDTO result = requestedBookService.getRequestedBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        System.out.println("Requested Book Name: " + result.getBookName());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testGetAllRequestedBooks() {
        List<RequestedBook> entities = List.of(entity);
        when(requestedBookRepository.findAll()).thenReturn(entities);
        when(requestedBookMapper.toDTO(entity)).thenReturn(dto);

        List<RequestedBookDTO> result = requestedBookService.getAllRequestedBooks();

        assertEquals(1, result.size());
        assertEquals("SpringBoot", result.get(0).getBookName());
        System.out.println("Total Requested Books: " + result.size());
        System.out.println("First Requested Book Name: " + result.get(0).getBookName());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void testUpdateRequestedBook() {
        RequestedBookDTO updatedDto = new RequestedBookDTO();
        updatedDto.setBookName("SpringBoot2");
        updatedDto.setAuthorName("Dasun");
        updatedDto.setCustomerName("Kasun");
        updatedDto.setRequestStatus(RequestedBook.RequestStatus.APPROVED);

        when(requestedBookRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(requestedBookRepository.save(any(RequestedBook.class))).thenReturn(entity);
        when(requestedBookMapper.toDTO(entity)).thenReturn(updatedDto);

        RequestedBookDTO result = requestedBookService.updateRequestedBook(1L, updatedDto);

        assertNotNull(result);
        assertEquals("SpringBoot2", result.getBookName());
        System.out.println("Requested Book Updated: " + result.getBookName() + " with status " + result.getRequestStatus());

    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void testDeleteRequestedBook() {
        when(requestedBookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(requestedBookRepository).deleteById(1L);

        assertDoesNotThrow(() -> requestedBookService.deleteRequestedBook(1L));
        verify(requestedBookRepository, times(1)).deleteById(1L);
        System.out.println("Requested Book with ID 1 deleted successfully.");
    }


    @Test
    @org.junit.jupiter.api.Order(6)
    void testDeleteRequestedBook_NotFound() {
        when(requestedBookRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            requestedBookService.deleteRequestedBook(1L);
        });

        assertEquals("Requested Book not found with id: 1", exception.getMessage());

    }





}


