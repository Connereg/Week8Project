package com.example.Week8Project.dto;

import com.example.Week8Project.model.Author;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class GetBooksDTO {
    private Long id;
    private String title;
    private int pages;
    private Date published;
    private GetAuthorDTO author;
    private List<GetGenreDTO> genreList;

}
