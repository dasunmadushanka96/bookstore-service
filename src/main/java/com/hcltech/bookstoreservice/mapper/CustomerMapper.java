package com.hcltech.bookstoreservice.mapper;

import com.hcltech.bookstoreservice.dto.CustomerDTO;
import com.hcltech.bookstoreservice.model.Customer;
import com.hcltech.bookstoreservice.model.PurchasedBookPK;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);

}
