package com.example.Week8Project.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadingListDTO {
    private String name;
    private List<GetBooksDTO> bookList;
}
