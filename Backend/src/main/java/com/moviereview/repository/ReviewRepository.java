package com.moviereview.repository;

import com.moviereview.model.Review;
import com.moviereview.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovie(Movie movie);
}