package com.example.book_review_api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    @NotNull(message = "userId is required")
    private Long userId;


    @NotNull(message = "Rating is required")
    @Min(value = 1,message = "Rating can be 1 at Lowest")
    @Max(value = 5,message = "Rating can be at most 5")
    private Integer rating;


    @Size(max = 500, message = "Comment can be of most 500 characters")
    private String comment;
}
