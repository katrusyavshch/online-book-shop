package org.example.bookshop.service;

import java.util.List;
import org.example.bookshop.dto.BookDto;
import org.example.bookshop.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    void deleteById(Long id);
}
