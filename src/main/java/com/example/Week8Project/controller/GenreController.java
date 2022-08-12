package com.example.Week8Project.controller;

import com.example.Week8Project.dto.CreateGenreDTO;
import com.example.Week8Project.dto.GetGenreDTO;
import com.example.Week8Project.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/genres")
    public GetGenreDTO createNewGenre(@RequestBody CreateGenreDTO createGenreDTO) {
        GetGenreDTO createdGenre = genreService.createGenre(createGenreDTO);
        return createdGenre;
    }

    @GetMapping("/genres/{id}")
    public GetGenreDTO getGenre(@PathVariable Long id) { return genreService.getGenre(id); }
}
