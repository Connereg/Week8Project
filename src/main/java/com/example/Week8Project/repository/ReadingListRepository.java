package com.example.Week8Project.repository;

import com.example.Week8Project.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {
    List<ReadingList> findAllByLibraryUserId(Long libraryUserId);
}
