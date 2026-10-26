package com.example.book_review_api.controller;


import com.example.book_review_api.dto.BookResponseDTO;
import com.example.book_review_api.dto.UserRequestDTO;
import com.example.book_review_api.dto.UserResponseDTO;
import com.example.book_review_api.service.BookService;
import com.example.book_review_api.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users",description = "Create users and look up the books they have added")
public class UserController {


private final UserService userService;
private final BookService bookService;

@PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto){
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
}

@GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
    return ResponseEntity.ok(userService.getAllUsers());
}

@GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
    return ResponseEntity.ok(userService.getUserById(id));
}

@GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDTO>> getBooksByUser(@PathVariable Long id){
    return ResponseEntity.ok(bookService.getBooksByUser(id));
}


}
