package com.moviereview.dto;

import lombok.Data;

@Data
public class CreateReviewRequest {
    private Long movieId;
    private String content;
    private Integer rating;
}