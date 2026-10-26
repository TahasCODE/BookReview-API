package com.example.book_review_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    private Long addedbyUserId;
    private String addedByUserName;
    private Double averageRating;
    private Integer reviewCount;
}
