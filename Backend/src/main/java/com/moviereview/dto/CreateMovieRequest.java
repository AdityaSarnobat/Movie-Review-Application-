package com.moviereview.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateMovieRequest {
    private String title;
    private String description;
    private String genre;
    private LocalDate releaseDate;
}