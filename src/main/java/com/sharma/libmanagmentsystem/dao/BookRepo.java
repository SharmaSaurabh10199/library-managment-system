package com.sharma.libmanagmentsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.sharma.libmanagmentsystem.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

}
