package com.example.book_review_api.repository;

import com.example.book_review_api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title,String author);

    List<Book> findByAddedById(Long userId);
}
