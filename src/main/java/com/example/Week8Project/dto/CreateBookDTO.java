package com.example.Week8Project.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateBookDTO {
    @NotBlank
    @NotNull
    private String title;

    @Min(1)
    private int pages;

    @NotNull
    private Date published;

}
