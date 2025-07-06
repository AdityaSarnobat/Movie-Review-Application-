package com.moviereview.service;


import com.moviereview.dto.CreateReviewRequest;
import com.moviereview.dto.ReviewDTO;
import com.moviereview.model.Movie;
import com.moviereview.model.Review;
import com.moviereview.model.User;
import com.moviereview.repository.MovieRepository;
import com.moviereview.repository.ReviewRepository;
import com.moviereview.repository.UserRepository;
import com.moviereview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewDTO createReview(CreateReviewRequest request, String username) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = Review.builder()
                .movie(movie)
                .user(user)
                .content(request.getContent())
                .rating(request.getRating())
                .build();
        review = reviewRepository.save(review);

        // Optionally update movie's average rating & count here

        return toDTO(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return reviewRepository.findByMovie(movie).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setMovieId(review.getMovie().getId());
        dto.setUserId(review.getUser().getId());
        dto.setUsername(review.getUser().getUsername());
        dto.setContent(review.getContent());
        dto.setRating(review.getRating());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}