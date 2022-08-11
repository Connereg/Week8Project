package com.example.Week8Project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"User\"")
@Getter
@Setter
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @OneToMany(mappedBy = "libraryUser")
    private List<ReadingList> userReadingList = new ArrayList<>();

}
