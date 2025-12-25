package com.example.bookstorecopy.repo;

import com.example.bookstorecopy.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AuthorRepo extends JpaRepository<Author,Long> {

    int countByCreatedDateAfter(LocalDateTime date);
    int countByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
