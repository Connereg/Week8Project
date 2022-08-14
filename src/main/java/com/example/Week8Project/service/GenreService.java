package com.example.Week8Project.service;

import com.example.Week8Project.dto.CreateAuthorDTO;
import com.example.Week8Project.dto.CreateGenreDTO;
import com.example.Week8Project.dto.GetAuthorDTO;
import com.example.Week8Project.dto.GetGenreDTO;
import com.example.Week8Project.exception.NotFoundException;
import com.example.Week8Project.model.Author;
import com.example.Week8Project.model.Genre;
import com.example.Week8Project.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Genre> createGenres(List<String> createdGenresNames) {
        List<Genre> allCreatedGenres = new ArrayList<>();

        for(int i = 0; i < createdGenresNames.size(); i++) {
            Genre newGenre = new Genre();
            newGenre.setName(createdGenresNames.get(i));
           genreRepository.save(newGenre);
           allCreatedGenres.add(newGenre);
        }

        return allCreatedGenres;
    }

    public GetGenreDTO getGenre(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found"));
        GetGenreDTO genreDTO = modelMapper.map(genre, GetGenreDTO.class );
        return genreDTO;
    }
}
