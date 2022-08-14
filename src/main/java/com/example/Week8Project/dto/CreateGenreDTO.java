package com.example.Week8Project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateGenreDTO {
    @NotBlank
    private String name;

}
