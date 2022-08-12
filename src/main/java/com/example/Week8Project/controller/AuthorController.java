package com.example.Week8Project.controller;

import com.example.Week8Project.dto.CreateAuthorDTO;
import com.example.Week8Project.dto.GetAuthorDTO;
import com.example.Week8Project.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/authors")
    public GetAuthorDTO createNewAuthor(@RequestBody CreateAuthorDTO createAuthorDTO) {
        GetAuthorDTO createdAuthor = authorService.createAuthor(createAuthorDTO);
        return createdAuthor;

    }

    @GetMapping("/authors/{id}")
    public GetAuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

}
