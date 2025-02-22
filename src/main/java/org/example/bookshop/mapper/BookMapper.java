package org.example.bookshop.mapper;

import org.example.bookshop.dto.BookDto;
import org.example.bookshop.dto.CreateBookRequestDto;
import org.example.bookshop.model.Book;

public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toBook(CreateBookRequestDto requestDto);
}
