package com.example.Week8Project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateUserReadingListDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Long libraryUserId;

    @NotNull
    private List<Long> listOfBookIds;

}
