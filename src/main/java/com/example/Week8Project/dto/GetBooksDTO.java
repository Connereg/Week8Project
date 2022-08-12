package com.example.Week8Project.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class GetBooksDTO {
    private Long id;
    private String title;
    private int pages;
    private LocalDate published;

}
