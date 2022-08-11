package com.example.Week8Project.service;

import com.example.Week8Project.dto.CreateBookDTO;
import com.example.Week8Project.dto.GetBooksDTO;
import com.example.Week8Project.dto.ReadingListDTO;
import com.example.Week8Project.model.ReadingList;
import com.example.Week8Project.repository.ReadingListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    @Lazy
    private LibraryUserService libraryUserService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReadingListDTO> getReadingListByUserId(Long userId) {
        List<ReadingListDTO> readingLists = new ArrayList<>();

        List<ReadingList> userReadingLists = readingListRepository.findAllByLibraryUserId(userId);
        for (ReadingList rl : userReadingLists) {
            ReadingListDTO rlDTO = modelMapper.map(rl, ReadingListDTO.class);
            readingLists.add(rlDTO);
        }

        return readingLists;
//        List<GetBooksDTO> booksInReadingList = (for reading in userreadinglist) {
//            userReadingLists.stream().map(readingList -> readingList.getBookList())
//                    .map(book -> modelMapper.map(book, GetBooksDTO.class)).collect(Collectors.toList());
//        }

    }



}
