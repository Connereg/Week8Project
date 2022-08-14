package com.example.Week8Project.controller;

import com.example.Week8Project.dto.CreateLibraryUserDTO;
import com.example.Week8Project.dto.GetLibraryUserDTO;
import com.example.Week8Project.dto.GetUserReadingListDTO;
import com.example.Week8Project.model.LibraryUser;
import com.example.Week8Project.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class LibraryUserController {
    @Autowired
    private LibraryUserService libraryUserService;

    @PostMapping("/users")
    public GetLibraryUserDTO createLibraryUser(@Valid @RequestBody CreateLibraryUserDTO createLibraryUserDTO) {
        return libraryUserService.createLibraryUser(createLibraryUserDTO);
    }

    @GetMapping("/{id}/reading_lists")
    public GetUserReadingListDTO getLibraryUser(@PathVariable Long id) {
        return libraryUserService.getUserReadingList(id);

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        libraryUserService.deleteUser(id);
    }
}
