package com.example.Week8Project.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
public class CreateBookDTO {
    @NotBlank
    private String title;

    @Min(1)
    private int pages;

    @Temporal(TemporalType.DATE)
    private Date published;

    @NotNull
    private Long authorId;

    @NotNull
    private List<String> genreNames;

    /////////////////////////  BUILD CONNECTIONS TO AUTHOR AND GENRE TABLES
//    @NotBlank
//    private Long authorId;
//
//    @NotBlank
//    private List<Long> genreIds;


}
