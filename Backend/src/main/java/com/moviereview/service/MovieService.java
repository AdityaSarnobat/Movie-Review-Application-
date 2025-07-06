package com.moviereview.service;

import com.moviereview.dto.CreateMovieRequest;
import com.moviereview.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    MovieDTO createMovie(CreateMovieRequest request);
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieById(Long id);
}