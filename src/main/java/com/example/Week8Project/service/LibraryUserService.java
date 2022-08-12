package com.example.Week8Project.service;

import com.example.Week8Project.dto.CreateLibraryUserDTO;
import com.example.Week8Project.dto.GetLibraryUserDTO;
import com.example.Week8Project.dto.GetUserReadingListDTO;
import com.example.Week8Project.exception.NotFoundException;
import com.example.Week8Project.model.LibraryUser;
import com.example.Week8Project.repository.LibraryUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryUserService {
    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private ReadingListService readingListService;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    @Lazy
//    private readingListService  ???

    public GetLibraryUserDTO createLibraryUser(CreateLibraryUserDTO createLibraryUserDTO) {
        LibraryUser libraryUser = modelMapper.map(createLibraryUserDTO, LibraryUser.class);
        return modelMapper.map(libraryUserRepository.save(libraryUser), GetLibraryUserDTO.class);
    }

    public GetUserReadingListDTO getUserReadingList(Long id) {
        GetUserReadingListDTO userReadingListDTO = libraryUserRepository
                .findById(id)
                .map(libraryUser -> modelMapper.map(libraryUser, GetUserReadingListDTO.class)).orElseThrow(() -> new NotFoundException("User not found (During reading list retrieval)"));
        userReadingListDTO.setUserReadingList(readingListService.getReadingListByUserId(id));
        return userReadingListDTO;
    }

    public void deleteUser(Long id) { libraryUserRepository.deleteById(id);}

//    public GetLibraryUserDTO getLibraryUser(Long id) {
//        LibraryUser libraryUser = libraryUserRepository.findById(id).orElseThrow(() -> new NotFoundException("User not Found"));
//        GetLibraryUserDTO libraryUserDTO = modelMapper.map(libraryUser, GetLibraryUserDTO.class);
//        return libraryUserDTO;
//    }




}
