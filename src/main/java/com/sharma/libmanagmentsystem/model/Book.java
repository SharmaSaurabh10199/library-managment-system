package com.sharma.libmanagmentsystem.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter // lombok ano for setter
@Getter // lombok anotation for getter
@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "cost")
    private int cost;

    private int isbn;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @CreationTimestamp
    private Date createdon;

    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn // @JoinColumn annotation is used to specify the column in the Book table that
                // corresponds to the foreign key that references the Author table. In this
                // case, the name of the column is author_id.
    private User user;

    // transaction
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

}
