package com.example.book_review_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;


    @Column(nullable = false, length = 150)
    private String author;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "added_by_user_id",nullable = false)
    private User addedBy;

     @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Review> reviews = new ArrayList<>();


}
