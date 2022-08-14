package com.example.Week8Project.controller;

import com.example.Week8Project.dto.CreateUserReadingListDTO;
import com.example.Week8Project.dto.GetUserReadingListDTO;
import com.example.Week8Project.dto.ReadingListDTO;
import com.example.Week8Project.service.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReadingListController {

    @Autowired
    ReadingListService readingListService;

    @PostMapping("users/{id}/reading_lists")
    public GetUserReadingListDTO createUserReadingList(@Valid @RequestBody CreateUserReadingListDTO createUserReadingListDTO) {
        return readingListService.createNewUserReadingList(createUserReadingListDTO);
    }

    // HAVING ISSUES WITH SAVING AND RETREIVING FROM THE READING LISTS REPOSITORY

    @GetMapping("/reading_lists")
    public List<ReadingListDTO> getAllReadingLists() {
        return readingListService.getAllReadingLists();
    }
}
