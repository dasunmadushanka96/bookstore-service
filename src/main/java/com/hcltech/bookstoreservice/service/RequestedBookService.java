package com.hcltech.bookstoreservice.service;

import com.hcltech.bookstoreservice.dto.RequestedBookDTO;
import com.hcltech.bookstoreservice.exception.BadRequestException;
import com.hcltech.bookstoreservice.model.RequestedBook;
import com.hcltech.bookstoreservice.mapper.RequestedBookMapper;
import com.hcltech.bookstoreservice.repository.RequestedBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestedBookService {

    private final RequestedBookRepository requestedBookRepository;
    RequestedBookMapper requestedBookMapper;

    public RequestedBookService(RequestedBookRepository requestedBookRepository, RequestedBookMapper requestedBookMapper) {
        this.requestedBookRepository = requestedBookRepository;
        this.requestedBookMapper = requestedBookMapper;
    }

    public RequestedBookDTO createRequestedBook(RequestedBookDTO dto) {

        RequestedBook entity = requestedBookMapper.toEntity(dto);
        entity.setRequestDate(java.time.LocalDate.now());
        // Set default request status to PENDING
        entity.setRequestStatus(RequestedBook.RequestStatus.PENDING);
        return requestedBookMapper.toDTO(requestedBookRepository.save(entity));
    }


    public RequestedBookDTO getRequestedBookById(Long id) {
        return requestedBookMapper.toDTO(requestedBookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Requested Book not found with id: " + id)));
    }

    public List<RequestedBookDTO> getAllRequestedBooks() {
        return requestedBookRepository.findAll().stream()
                .map(requestedBookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RequestedBookDTO updateRequestedBook(Long id, RequestedBookDTO dto) {
        RequestedBook existingEntity = requestedBookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Requested Book not found with id: " + id));

        if (existingEntity != null) {

        existingEntity.setBookName(dto.getBookName());
        existingEntity.setAuthorName(dto.getAuthorName());
        existingEntity.setCustomerName(dto.getCustomerName());
        existingEntity.setRequestStatus(dto.getRequestStatus());

        return requestedBookMapper.toDTO(requestedBookRepository.save(existingEntity));
    }
        return null;
    }

    public void deleteRequestedBook(Long id) {
        if (requestedBookRepository.existsById(id)) {
            requestedBookRepository.deleteById(id);
        } else {
            throw new BadRequestException("Requested Book not found with id: " + id);
        }
    }

}
