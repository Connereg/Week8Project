package com.example.Week8Project.model;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @Min(1)
    private int pages;

    @Temporal(TemporalType.DATE)
    private Date published;

    @ManyToMany
    private List<ReadingList> readingLists = new ArrayList<>();

    @ManyToOne
    private Author author;

    @ManyToMany(mappedBy = "bookList")
    private List<Genre> genreList = new ArrayList<>();

    public void addGenre(Genre genre) {
        genreList.add(genre);
    }

    public void addReadingList(ReadingList readinglist) {
        readingLists.add(readinglist);
    }
}
