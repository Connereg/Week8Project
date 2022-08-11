package com.example.Week8Project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetBooksDTO {
    private Long id;
    private String title;
    private int pages;
    private Date published;

}
