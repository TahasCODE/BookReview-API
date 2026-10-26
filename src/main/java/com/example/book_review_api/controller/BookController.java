package com.example.book_review_api.controller;

import com.example.book_review_api.dto.BookRequestDTO;
import com.example.book_review_api.dto.BookResponseDTO;
import com.example.book_review_api.service.BookService;
import com.example.book_review_api.service.UserService;
import com.sun.net.httpserver.HttpServer;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Tag(name = "Books",description = "Create,read,update,delete and search books")
public class BookController {
    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(dto));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getallBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id,@Valid @RequestBody BookRequestDTO dto){
        return ResponseEntity.ok(bookService.updateBook(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDTO>> searchBooks(@RequestParam String keyword){
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }


    
}
