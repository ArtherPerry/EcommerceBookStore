package com.example.bookstorecopy.repo;

import com.example.bookstorecopy.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BookRepo extends JpaRepository<Book,Long> {
    int countByCreatedDateAfter(LocalDateTime date);
    int countByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
