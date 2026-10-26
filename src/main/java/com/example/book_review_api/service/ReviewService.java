package com.example.book_review_api.service;

import com.example.book_review_api.dto.ReviewRequestDTO;
import com.example.book_review_api.dto.ReviewResponseDTO;
import com.example.book_review_api.entity.Book;
import com.example.book_review_api.entity.Review;
import com.example.book_review_api.entity.User;
import com.example.book_review_api.exception.DuplicateReviewException;
import com.example.book_review_api.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookService bookService;
    private final UserService userService;


    public ReviewResponseDTO addReview(Long bookId, ReviewRequestDTO dto){
        Book book = bookService.getBookEntityById(bookId);
        User user = userService.getUserEntityById(dto.getUserId());

        reviewRepository.findByBookIdAndUserId(bookId, dto.getUserId())
                .ifPresent(existing -> {
                    throw new DuplicateReviewException(
                         "User" + dto.getUserId() + "has already reviewed book " + bookId);
                        });
        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        Review saved = reviewRepository.save(review);
        return toResponseDTO(saved);
    }

    public List<ReviewResponseDTO> getReviewsForBook(Long bookId){
        bookService.getBookEntityById(bookId);
        return reviewRepository.findByBookId(bookId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private ReviewResponseDTO toResponseDTO(Review review) {
        return new ReviewResponseDTO(
                 review.getId(),
                 review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                review.getBook().getId(),
                review.getUser().getId(),
                review.getUser().getName()
        );
    }

}
