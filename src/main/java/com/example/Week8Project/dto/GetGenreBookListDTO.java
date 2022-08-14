package com.example.Week8Project.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetGenreBookListDTO {
    private String name;

    List<GenreBooksDTO> bookList;

}
