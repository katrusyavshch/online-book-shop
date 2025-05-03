package org.example.bookshop.mapper;

import org.example.bookshop.config.MapperConfig;
import org.example.bookshop.dto.cartitem.CartItemDto;
import org.example.bookshop.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shoppingCart", ignore = true)
    CartItem toModel(CartItemDto cartItemDto);
}
