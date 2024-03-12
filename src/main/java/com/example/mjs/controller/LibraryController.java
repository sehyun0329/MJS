package com.example.mjs.controller;

import com.example.mjs.model.Book;
import com.example.mjs.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book-list")
public class LibraryController {
//    @RequestMapping("/")
//    @GetMapping("/")
//    public String home(Model model)
//    {
//        return "bookRent/book-list";
//    }

    private final BookRepository bookRepository;

    public LibraryController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "bookRent/book-list";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
        return "bookRent/book-details";
    }

    @GetMapping("/rent/{id}")
    public String rentBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isRented()) {
            book.setRented(true);
            bookRepository.save(book);
        }

        return "redirect:/book-list/";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.isRented()) {
            book.setRented(false);
            bookRepository.save(book);
        }

        return "redirect:/book-list/";
    }
}
