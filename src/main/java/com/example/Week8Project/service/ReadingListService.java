package com.example.Week8Project.service;

import com.example.Week8Project.dto.*;
import com.example.Week8Project.model.Book;
import com.example.Week8Project.model.LibraryUser;
import com.example.Week8Project.model.ReadingList;
import com.example.Week8Project.repository.LibraryUserRepository;
import com.example.Week8Project.repository.ReadingListRepository;
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
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    @Lazy
    private LibraryUserService libraryUserService;


    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    public GetUserReadingListDTO createNewUserReadingList(@Valid @RequestBody CreateUserReadingListDTO createUserReadingListDTO) {
        ReadingList newReadingList = new ReadingList();
        List<Book> bookList = new ArrayList<>();
        LibraryUser targetedUser = libraryUserService.getLibraryUser(createUserReadingListDTO.getLibraryUserId());
        newReadingList.setLibraryUser(targetedUser);

        List<Long> listOfBookIds = createUserReadingListDTO.getListOfBookIds();
        for(Long bookId : listOfBookIds) {
            GetBooksDTO bookDTOToAdd = bookService.getBookById(bookId);
            Book bookToAdd = modelMapper.map(bookDTOToAdd, Book.class);
            bookToAdd.addReadingList(newReadingList);
            bookList.add(bookToAdd);

        }
        newReadingList.setBookList(bookList);
        newReadingList.setName(createUserReadingListDTO.getName());
        targetedUser.getUserReadingList().add(newReadingList);
        readingListRepository.save(newReadingList);

       return modelMapper.map(newReadingList, GetUserReadingListDTO.class );
    }

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

    public List<ReadingListDTO> getAllReadingLists() {
        List<ReadingListDTO> allReadingLists = readingListRepository.findAll()
                .stream()
                .map(rl -> modelMapper.map(rl, ReadingListDTO.class))
                .collect(Collectors.toList());
        return allReadingLists;
    }



}
