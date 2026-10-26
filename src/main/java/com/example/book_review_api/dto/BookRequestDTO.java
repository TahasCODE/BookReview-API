package com.example.book_review_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(max = 150,message = "Title can be of most 150 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 200,message = "Author can be of most 200 characters")
    private String author;

    @Size(max = 1000,message = "Description can be of most 1000 characters")
    private String description;

    @NotNull(message = "addedByUserId is required")
    private Long addedByUserId;
}
