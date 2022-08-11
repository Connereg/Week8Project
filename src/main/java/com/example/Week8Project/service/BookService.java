package com.example.Week8Project.service;

import com.example.Week8Project.dto.CreateBookDTO;
import com.example.Week8Project.dto.GetBooksDTO;
import com.example.Week8Project.exception.NotFoundException;
import com.example.Week8Project.model.Book;
import com.example.Week8Project.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;


    public GetBooksDTO createBook(CreateBookDTO createBookDTO) {
        Book book = modelMapper.map(createBookDTO, Book.class);
        return modelMapper.map(bookRepository.save(book), GetBooksDTO.class);
    }

    public GetBooksDTO updateBook(Long id, CreateBookDTO createBookDTO) {
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(createBookDTO.getTitle());
        bookToUpdate.setPages(createBookDTO.getPages());
        bookToUpdate.setPublished(createBookDTO.getPublished());
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
        bookRepository.deleteById(id);
    }
}
