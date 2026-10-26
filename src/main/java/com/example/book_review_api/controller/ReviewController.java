package com.example.book_review_api.controller;


import com.example.book_review_api.dto.ReviewRequestDTO;
import com.example.book_review_api.dto.ReviewResponseDTO;
import com.example.book_review_api.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/{bookId}/reviews")
@Tag(name = "Reviews",description = "Add a review to a book and List a book's reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewResponseDTO> addReview(@PathVariable Long bookId, @Valid @RequestBody ReviewRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(bookId,dto));
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsForBook(@PathVariable Long bookId){
        return ResponseEntity.ok(reviewService.getReviewsForBook(bookId));

    }

}
