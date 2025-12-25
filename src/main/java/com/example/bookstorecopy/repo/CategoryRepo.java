package com.example.bookstorecopy.repo;

import com.example.bookstorecopy.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    int countByCreatedDateAfter(LocalDateTime date);
    int countByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
