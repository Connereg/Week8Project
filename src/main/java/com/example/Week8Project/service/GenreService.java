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

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ModelMapper modelMapper;

    public GetGenreDTO createGenre(CreateGenreDTO createGenreDTO) {
        if (!genreRepository.existsById(createGenreDTO.getId())) {
            Genre genre = modelMapper.map(createGenreDTO, Genre.class);
            genreRepository.save(genre);
            return modelMapper.map(genre, GetGenreDTO.class);
        }
        else {
            return modelMapper.map(genreRepository.findById(createGenreDTO.getId()), GetGenreDTO.class);
        }
    }

    public GetGenreDTO getGenre(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found"));
        GetGenreDTO genreDTO = modelMapper.map(genre, GetGenreDTO.class );
        return genreDTO;
    }
}
