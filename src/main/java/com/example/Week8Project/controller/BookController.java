package com.example.Week8Project.controller;

import com.example.Week8Project.dto.CreateAuthorDTO;
import com.example.Week8Project.dto.CreateBookDTO;
import com.example.Week8Project.dto.GetAuthorDTO;
import com.example.Week8Project.dto.GetBooksDTO;
import com.example.Week8Project.model.Book;
import com.example.Week8Project.service.AuthorService;
import com.example.Week8Project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping("/books")
    public GetBooksDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        // Create AuthorDTO from the CreateBookDTO authorname property

       GetBooksDTO createdBook = bookService.createBook(createBookDTO);

       return createdBook;
    }
    @GetMapping("/books")
    public List<GetBooksDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public GetBooksDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    @PutMapping("/books/{id}")
    public GetBooksDTO updateBook(@PathVariable Long id, @Valid @RequestBody CreateBookDTO createBookDTO) {
        GetBooksDTO updatedBook = bookService.updateBook(id, createBookDTO);
        return updatedBook;
    }
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
