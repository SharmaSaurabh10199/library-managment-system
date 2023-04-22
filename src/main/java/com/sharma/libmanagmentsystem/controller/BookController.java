package com.sharma.libmanagmentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharma.libmanagmentsystem.model.Book;
import com.sharma.libmanagmentsystem.requests.BookCreateRequest;
import com.sharma.libmanagmentsystem.services.BookService;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    // add book to our database
    @PostMapping("/book")
    public Book addBook(@RequestBody BookCreateRequest bookCreateRequest) {
        return bookService.addBook(bookCreateRequest.to());
    }

    // get all books
    @GetMapping("/book/all")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    // get a book
    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        return bookService.getBookById(bookId);
    }

}
