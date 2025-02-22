package org.example.bookshop.dto;

import lombok.Data;

@Data
public class CreateBookRequestDto {
    private String title;
    private String author;
}
