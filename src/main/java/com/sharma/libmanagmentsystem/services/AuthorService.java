package com.sharma.libmanagmentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sharma.libmanagmentsystem.dao.AuthorRepo;
import com.sharma.libmanagmentsystem.model.Author;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    // author will exist or it will not
    // if it will not exist, we will have to create author
    // if exists, then use

    public Author createOrGetAuthor(Author author) {
        Author authorFromDb = authorRepo.findAuthor(author.getName());
        if (authorFromDb == null) {
            authorFromDb = authorRepo.save(author);
        }

        return authorFromDb;

    }

}
