package org.example.bookshop;

import java.math.BigDecimal;
import org.example.bookshop.model.Book;
import org.example.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookshopApplication {
    private final BookService bookService;

    @Autowired
    public BookshopApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();
                book.setAuthor("John Doe");
                book.setTitle("Java Programming");
                book.setPrice(BigDecimal.valueOf(800));
                book.setIsbn("123456789");

                bookService.save(book);
                System.out.println(bookService.findAll());
            }
        };
    }
}
