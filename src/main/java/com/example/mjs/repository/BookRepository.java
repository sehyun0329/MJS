package com.example.mjs.repository;

import com.example.mjs.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByRented(boolean rented);
}
