package com.example.Week8Project.repository;

import com.example.Week8Project.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
}
