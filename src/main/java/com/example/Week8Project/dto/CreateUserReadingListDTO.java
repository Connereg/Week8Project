package com.example.Week8Project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserReadingListDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String name;

    private Long libraryUserId;

}
