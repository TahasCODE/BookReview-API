package com.example.book_review_api.repository;

import com.example.book_review_api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByBookId(Long bookId);

    Optional<Review> findByBookIdAndUserId(Long bookId,Long userId);
}
