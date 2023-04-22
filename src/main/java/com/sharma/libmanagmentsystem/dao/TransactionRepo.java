package com.sharma.libmanagmentsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharma.libmanagmentsystem.model.Book;
import com.sharma.libmanagmentsystem.model.Transaction;
import com.sharma.libmanagmentsystem.model.TransactionType;
import com.sharma.libmanagmentsystem.model.User;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book,
            User user, TransactionType issue);

}
