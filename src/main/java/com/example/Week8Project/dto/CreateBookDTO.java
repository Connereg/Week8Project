package com.example.Week8Project.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Data
public class CreateBookDTO {
    @NotBlank
    @NotNull
    private String title;

    @Min(1)
    private int pages;

    @NotBlank
    private LocalDate published;

    /////////////////////////  BUILD CONNECTIONS TO AUTHOR AND GENRE TABLES
//    @NotBlank
//    private Long authorId;
//
//    @NotBlank
//    private List<Long> genreIds;


}
