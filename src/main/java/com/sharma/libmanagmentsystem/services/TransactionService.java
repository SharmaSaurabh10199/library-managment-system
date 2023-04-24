package com.sharma.libmanagmentsystem.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharma.libmanagmentsystem.dao.TransactionRepo;
import com.sharma.libmanagmentsystem.model.Book;
import com.sharma.libmanagmentsystem.model.Transaction;
import com.sharma.libmanagmentsystem.model.TransactionStatus;
import com.sharma.libmanagmentsystem.model.TransactionType;
import com.sharma.libmanagmentsystem.model.User;
import org.springframework.beans.factory.annotation.Value;
// import lombok.Value;

@Service
public class TransactionService {

    @Value("${user.book.quota}")
    int userBookQuota;
    @Value("${book.return.days}")
    int daysLimit;

    @Value("${book.fine.perday}")
    int finePerDay;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    public String issuBook(int userId, int bookId) throws Exception {
        // check if that student present or not
        // check if the book is present or book is awailable or not
        // students book limit or quota
        // create transaction with initial state as pending
        // transaction type to issue
        // make the book unavailable and assign it to student
        // update the transaction successfull
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new Exception("invalid student, unable to issue the book");

        }
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            throw new Exception("book not present, unable to issue the book");
        }

        if (book.getUser() != null) {
            throw new Exception("book is already issed to someone else, unable to issue the book");
        }
        // if book quota is reached
        if (user.getBookList().size() >= userBookQuota) {
            throw new Exception("student quota has reached, unable to issue the book");
        }

        // lets create the transaction before saving it
        Transaction transaction = Transaction.builder().book(book).user(user)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionId(UUID.randomUUID().toString()).build();

        // save the transaction
        try {

            transactionRepo.save(transaction);
            book.setUser(user);
            bookService.addBook(book);
            // update the payment to success
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepo.save(transaction);

            // suppose it fails

        } catch (Exception e) {
            // fallback
            book.setUser(null);
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepo.save(transaction);

        }
        return transaction.getTransactionId();
    }

    public void returnBook(int userId, int bookId) throws Exception {

        // check wheather the book is assigned to student or not
        // check the fine and update the transaction
        // we will create the txn with pending status
        // remove the student as assignee and make the book available // set userId to
        // null
        // update the txn with status - success
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (user == null || book == null || book.getUser() == null || book.getUser().getUserId() != userId) {
            throw new Exception(" Book or student is either not present, or book is not assignet to this student");
        }

        List<Transaction> issueTxnList = transactionRepo.findByBookAndUserAndTransactionTypeOrderByIdDesc(book,
                user,
                TransactionType.ISSUE);

        Transaction issueTnx = issueTxnList.get(0);

        // to check the fine scenerio
        long issueTimeInMiliSec = issueTnx.getUpdatedOn().getTime();
        long currentTimeInMiliSec = System.currentTimeMillis();
        long timeDiff = currentTimeInMiliSec - issueTimeInMiliSec;

        long numberOfDyasPassed = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        int fine = 0;
        if (numberOfDyasPassed > daysLimit) {
            fine = (int) ((numberOfDyasPassed - daysLimit) * finePerDay);
        }

        Transaction transaction = Transaction.builder().book(book).user(user)
                .transactionType(TransactionType.RETURN)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionId(UUID.randomUUID().toString())
                .fine(fine).build();

        transactionRepo.save(transaction);

        try {

            book.setUser(null);
            bookService.addBook(book);
            // update the payment to success
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepo.save(transaction);

            // suppose it fails

        } catch (Exception e) {
            // fallback
            book.setUser(user);
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepo.save(transaction);

        }

    }

}
