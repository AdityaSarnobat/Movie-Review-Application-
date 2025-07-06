package com.moviereview.service;

import com.moviereview.dto.CreateReviewRequest;
import com.moviereview.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(CreateReviewRequest request, String username);
    List<ReviewDTO> getReviewsByMovie(Long movieId);
}