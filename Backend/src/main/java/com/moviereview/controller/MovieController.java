package com.moviereview.controller;

import com.moviereview.model.Movie;
import com.moviereview.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    // List all movies
    @GetMapping("/")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";
    }

    // Movie details
    @GetMapping("/movies/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieRepository.findById(id).orElse(null));
        return "movie-detail";
    }

    // Add form
    @GetMapping("/movies/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addForm(Model model) {
        model.addAttribute("movie", null);
        return "movie-form";
    }

    // Add movie POST
    @PostMapping("/movies/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addMovie(@ModelAttribute Movie movie) {
        movieRepository.save(movie);
        return "redirect:/";
    }

    // Edit form
    @GetMapping("/movies/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieRepository.findById(id).orElse(null));
        return "movie-form";
    }

    // Edit movie POST
    @PostMapping("/movies/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editMovie(@PathVariable Long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieRepository.save(movie);
        return "redirect:/movies/" + id;
    }

    // Delete movie POST
    @PostMapping("/movies/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return "redirect:/";
    }
}