package com.example.Week8Project.service;

import com.example.Week8Project.dto.*;
import com.example.Week8Project.exception.NotFoundException;
import com.example.Week8Project.model.Author;
import com.example.Week8Project.model.Book;
import com.example.Week8Project.model.Genre;
import com.example.Week8Project.repository.BookRepository;
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

        for (int i = 0; i < createdGenresNames.size(); i++) {
            if (genreRepository.findByName(createdGenresNames.get(i)) == null) {
                Genre newGenre = new Genre();
                newGenre.setName(createdGenresNames.get(i));
                genreRepository.save(newGenre);
                allCreatedGenres.add(newGenre);
            } else {
                allCreatedGenres.add(modelMapper.map(genreRepository
                        .findByName(createdGenresNames
                                .get(i)), Genre.class));
            }
        }
        return allCreatedGenres;
    }

    public List<GenreBooksDTO> getAllBooksFromGenreId(Long id) {
        List<GenreBooksDTO> listOfBookDTO = new ArrayList<>();

        Genre genreById = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre Not Found"));
        List<Book> listOfBooksByGenre = genreById.getBookList();
        for (Book book : listOfBooksByGenre) {
            GenreBooksDTO genreBookDTO = modelMapper.map(book, GenreBooksDTO.class);
            listOfBookDTO.add(genreBookDTO);
        }

        return listOfBookDTO;
    }

    public GetGenreDTO getGenre(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found"));
        GetGenreDTO genreDTO = modelMapper.map(genre, GetGenreDTO.class );
        return genreDTO;
    }
}
