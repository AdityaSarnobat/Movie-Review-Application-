package com.moviereview.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private LocalDate releaseDate;
    private Double averageRating;
    private Integer reviewCount;
}