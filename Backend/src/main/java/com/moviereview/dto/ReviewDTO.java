package com.moviereview.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewDTO {
    private Long id;
    private Long movieId;
    private Long userId;
    private String username;
    private String content;
    private int rating;
    private LocalDateTime createdAt;
}