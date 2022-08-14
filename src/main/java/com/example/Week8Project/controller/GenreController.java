package com.example.Week8Project.controller;

import com.example.Week8Project.dto.*;
import com.example.Week8Project.model.Genre;
import com.example.Week8Project.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/genre")
    public List<Genre> createNewGenres(@RequestBody List<String> createdGenresNames) {
        List<Genre> createdGenres = genreService.createGenres(createdGenresNames);
        return createdGenres;
    }

    @GetMapping("/genre/{id}")
    public GetGenreDTO getGenre(@PathVariable Long id) { return genreService.getGenre(id); }

    @GetMapping("/genre/{id}/books")
    public GetGenreBookListDTO getBooksFromGenre(@PathVariable Long id) {
        GetGenreDTO genre = genreService.getGenre(id);
        List<GenreBooksDTO> allBooksOfGenreDTO = genreService.getAllBooksFromGenreId(id);

        GetGenreBookListDTO genreBookListOutput = new GetGenreBookListDTO();
        genreBookListOutput.setName(genre.getName());
        genreBookListOutput.setBookList(allBooksOfGenreDTO);

        return genreBookListOutput;
    }
}
