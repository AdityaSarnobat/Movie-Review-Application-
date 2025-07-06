package com.moviereview.service;

import com.moviereview.dto.CreateMovieRequest;
import com.moviereview.dto.MovieDTO;
import com.moviereview.model.Movie;
import com.moviereview.repository.MovieRepository;
import com.moviereview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDTO createMovie(CreateMovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .genre(request.getGenre())
                .releaseDate(request.getReleaseDate())
                .averageRating(0.0)
                .reviewCount(0)
                .build();
        movie = movieRepository.save(movie);
        return toDTO(movie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        return toDTO(movie);
    }

    private MovieDTO toDTO(Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .releaseDate(movie.getReleaseDate())
                .averageRating(movie.getAverageRating())
                .reviewCount(movie.getReviewCount())
                .build();
    }
}