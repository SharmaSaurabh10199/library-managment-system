package com.sharma.libmanagmentsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharma.libmanagmentsystem.dao.BookRepo;
import com.sharma.libmanagmentsystem.model.Author;
import com.sharma.libmanagmentsystem.model.Book;

@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;

    // never inject the userRepo, the cross repos here , might have to inject
    // services
    @Autowired
    AuthorService authorService;

    public Book addBook(Book book) {
        // we dont want to inject author if it already existing
        Author author = authorService.createOrGetAuthor(book.getAuthor());

        // setting the book is neccessary prior to saving the book
        book.setAuthor(author);

        bookRepo.save(book);
        return null;
    }

    public List<Book> getBooks() {

        return bookRepo.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepo.findById(bookId).orElse(null);
    }

}
