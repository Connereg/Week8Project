package com.example.Week8Project.service;

import com.example.Week8Project.dto.CreateBookDTO;
import com.example.Week8Project.dto.GetBooksDTO;
import com.example.Week8Project.dto.GetGenreDTO;
import com.example.Week8Project.exception.NotFoundException;
import com.example.Week8Project.model.Author;
import com.example.Week8Project.model.Book;
import com.example.Week8Project.model.Genre;
import com.example.Week8Project.repository.AuthorRepository;
import com.example.Week8Project.repository.BookRepository;
import com.example.Week8Project.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ModelMapper modelMapper;


    public GetBooksDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        Author author = authorRepository.findById(createBookDTO.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
        List<Genre> genreList = genreService.createGenres(createBookDTO.getGenreNames());
        Book book = modelMapper.map(createBookDTO, Book.class);
        book.setAuthor(author);
        book.setGenreList(genreList);
        for (Genre genre : genreList) {
            genre.addBook(book);
        }
        // authorName in createBookDTO => use authorName to construct new Author obj
        // Convert authorDTO to author obj
        // Add my author to my Book Obj
        return modelMapper.map(bookRepository.save(book), GetBooksDTO.class);
    }

    public GetBooksDTO updateBook(Long id, CreateBookDTO createBookDTO) {
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(createBookDTO.getTitle());
        bookToUpdate.setPages(createBookDTO.getPages());
        bookToUpdate.setPublished(createBookDTO.getPublished());
        Author updatedAuthor = authorRepository.findById(createBookDTO.getAuthorId()).orElseThrow(() -> new NotFoundException("Author Not Found"));
        bookToUpdate.setAuthor(updatedAuthor);
        List<Genre> updatedGenreList = genreService.createGenres(createBookDTO.getGenreNames());
        bookToUpdate.setGenreList(updatedGenreList);
        bookRepository.save(bookToUpdate);
        GetBooksDTO bookToUpdateDTO = modelMapper.map(bookToUpdate, GetBooksDTO.class);
        return bookToUpdateDTO;
    }

    public List<GetBooksDTO> getAllBooks() {
        List<GetBooksDTO> allBookList = bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, GetBooksDTO.class))
                .collect(Collectors.toList());
        return allBookList;
    }

    public GetBooksDTO getBookById(Long id) {
        GetBooksDTO bookDTO = bookRepository.findById(id)
                .map(book -> modelMapper.map(book, GetBooksDTO.class)).orElseThrow(() -> new NotFoundException("Book not found"));
        return bookDTO;
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Book not found");
        }


    }
}
