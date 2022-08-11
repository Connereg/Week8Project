package com.example.Week8Project.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetUserReadingListDTO {
    private Long id;
    private String username;
    private List<ReadingListDTO> userReadingList;
}
