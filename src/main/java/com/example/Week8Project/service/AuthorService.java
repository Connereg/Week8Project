package com.example.Week8Project.service;

import com.example.Week8Project.dto.CreateAuthorDTO;
import com.example.Week8Project.dto.GetAuthorDTO;
import com.example.Week8Project.exception.NotFoundException;
import com.example.Week8Project.model.Author;
import com.example.Week8Project.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    ModelMapper modelMapper;

    public GetAuthorDTO createAuthor(CreateAuthorDTO createAuthorDTO) {
        if (!authorRepository.existsById(createAuthorDTO.getId())) {
            Author author = modelMapper.map(createAuthorDTO, Author.class);
            authorRepository.save(author);
            return modelMapper.map(author, GetAuthorDTO.class);
        }
        else {
            return modelMapper.map(authorRepository.findById(createAuthorDTO.getId()), GetAuthorDTO.class);
        }

    }

    public GetAuthorDTO getAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author Not Found"));
        GetAuthorDTO authorDTO = modelMapper.map(author, GetAuthorDTO.class );
        return authorDTO;
    }
}
