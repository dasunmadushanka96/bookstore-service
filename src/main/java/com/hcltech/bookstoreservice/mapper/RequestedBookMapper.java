package com.hcltech.bookstoreservice.mapper;

import com.hcltech.bookstoreservice.dto.RequestedBookDTO;
import com.hcltech.bookstoreservice.model.RequestedBook;
import org.springframework.stereotype.Component;

@Component
public class RequestedBookMapper {

    public RequestedBookDTO toDTO(RequestedBook entity) {
        RequestedBookDTO dto = new RequestedBookDTO();

        dto.setId(entity.getId());
        dto.setBookName(entity.getBookName());
        dto.setAuthorName(entity.getAuthorName());
        dto.setRequestDate(entity.getRequestDate());
        dto.setRequestStatus(entity.getRequestStatus());
        dto.setCustomerName(entity.getCustomerName());

        return dto;
    }

    public RequestedBook toEntity(RequestedBookDTO dto) {
        RequestedBook entity = new RequestedBook();

        entity.setId(dto.getId());
        entity.setBookName(dto.getBookName());
        entity.setAuthorName(dto.getAuthorName());
        entity.setRequestDate(dto.getRequestDate());
        entity.setRequestStatus(dto.getRequestStatus());
        entity.setCustomerName(dto.getCustomerName());

        return entity;
    }

}
