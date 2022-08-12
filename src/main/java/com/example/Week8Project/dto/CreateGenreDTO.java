package com.example.Week8Project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateGenreDTO {
    private Long id;
    @NotBlank
    private String name;

}
