package com.example.book_review_api.service;


import com.example.book_review_api.dto.BookRequestDTO;
import com.example.book_review_api.dto.BookResponseDTO;
import com.example.book_review_api.entity.Book;
import com.example.book_review_api.entity.Review;
import com.example.book_review_api.entity.User;
import com.example.book_review_api.exception.ResourceNotFoundException;
import com.example.book_review_api.repository.BookRepository;
import jakarta.el.MethodNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final UserService userService;

    public BookResponseDTO createBook(BookRequestDTO dto){
    User addedBy = userService.getUserEntityById(dto.getAddedByUserId());
    Book book = new Book();
    book.setTitle(dto.getTitle());
    book.setAuthor(dto.getAuthor());
    book.setDescription(dto.getDescription());
    book.setAddedBy(addedBy);
    Book saved = bookRepository.save(book);
    return toResponseDTO(saved);
    }

    public List<BookResponseDTO> getallBooks(){
        return bookRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public BookResponseDTO getBookById(Long id){
        return toResponseDTO(getBookEntityById(id));
    }

    public BookResponseDTO updateBook(Long id,BookRequestDTO dto){
        Book book = getBookEntityById(id);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());

        if(!dto.getAddedByUserId().equals(book.getAddedBy().getId())){
            book.setAddedBy(userService.getUserEntityById(dto.getAddedByUserId()));
        }

        Book saved = bookRepository.save(book);
        return toResponseDTO(saved);
    }

    public void deleteBook(Long id){
        Book book = getBookEntityById(id);
        bookRepository.delete(book);
    }

    public List<BookResponseDTO> searchBooks(String keyword){
        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword,keyword)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    
    public List<BookResponseDTO> getBooksByUser(Long user_id){
        userService.getUserEntityById(user_id);
        return bookRepository.findByAddedById(user_id)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Book getBookEntityById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This resource is not found " + id));
    }

    private BookResponseDTO toResponseDTO(Book book) {
    List<Review> reviews = book.getReviews();
    double averageRating = reviews.isEmpty()
            ? 0.0
            :reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
         double roundedAverage = Math.round(averageRating * 10.0) / 10.0;
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getCreatedAt(),
                book.getAddedBy().getId(),
                book.getAddedBy().getName(),
                roundedAverage,
                reviews.size()
        );
    }

}
