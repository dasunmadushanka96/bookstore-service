package com.hcltech.bookstoreservice.mapper;


import com.hcltech.bookstoreservice.dto.BookInventoryDTO;
import com.hcltech.bookstoreservice.model.BookInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "bookId", source = "book.id")
    BookInventoryDTO toDto(BookInventory inventory);

    @Mapping(target = "book.id", source = "bookId")
    BookInventory toEntity(BookInventoryDTO inventoryDTO);

}
