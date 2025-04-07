package org.example.bookshop.repository.book;

import org.example.bookshop.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = "categories")
    @Query("FROM Book b JOIN FETCH b.categories c WHERE c.id = :id")
    Page<Book> findAllByCategoryId(@Param("id") Long categoryId, Pageable pageable);
}
