package com.example.Week8Project.repository;

import com.example.Week8Project.model.Author;
import com.example.Week8Project.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}
