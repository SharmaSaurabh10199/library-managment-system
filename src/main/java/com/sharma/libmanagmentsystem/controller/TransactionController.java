package com.sharma.libmanagmentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharma.libmanagmentsystem.services.TransactionService;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/issue")
    public void isseBook(@RequestParam("userId") int userId, @RequestParam int bookId) throws Exception {
        // need a user to issue the book
        transactionService.issuBook(userId, bookId);

    }

    @PostMapping("transaction/return")
    public void returnBook(@RequestParam("userId") int userId, @RequestParam int bookId) throws Exception {
        transactionService.returnBook(userId, bookId);
    }

}
