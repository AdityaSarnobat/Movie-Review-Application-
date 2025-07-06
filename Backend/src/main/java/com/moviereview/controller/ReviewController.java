package com.moviereview.controller;

import com.moviereview.model.*;
import com.moviereview.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/movie/{movieId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reviewRepository.findByMovie(movie));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, Authentication authentication) {
        Movie movie = movieRepository.findById(req.getMovieId()).orElse(null);
        if (movie == null) return ResponseEntity.badRequest().build();
        User user = userRepository.findByUsername(authentication.getName()).orElse(null);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Review review = Review.builder()
                .movie(movie)
                .user(user)
                .content(req.getContent())
                .rating(req.getRating())
                .build();
        return ResponseEntity.ok(reviewRepository.save(review));
    }

    @PutMapping("/{reviewId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest req, Authentication authentication) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) return ResponseEntity.notFound().build();
        if (!review.getUser().getUsername().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        review.setContent(req.getContent());
        review.setRating(req.getRating());
        return ResponseEntity.ok(reviewRepository.save(review));
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, Authentication authentication) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) return ResponseEntity.notFound().build();
        if (!review.getUser().getUsername().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        reviewRepository.delete(review);
        return ResponseEntity.noContent().build();
    }
}

@Data
class ReviewRequest {
    private Long movieId;
    private String content;
    private int rating;
}