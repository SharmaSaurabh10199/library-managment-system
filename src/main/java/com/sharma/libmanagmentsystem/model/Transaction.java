package com.sharma.libmanagmentsystem.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter // lombok ano for setter
@Getter // lombok anotation for getter
@Entity
@Builder

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // for sake of privacy we don't wanna display actual nos
    private String transactionId;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Book book;

    private int fine;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    // The code @Enumerated(value = EnumType.STRING) is a JPA annotation used to map
    // an enumerated value from an entity class attribute to a String column type in
    // a relational database table. It allows you to store the String representation
    // of the enumerated value in the database column instead of its ordinal value
    // or its default name.
    // This annotation is often used in conjunction with the @Column annotation,
    // where the name attribute in @Column can be used to specify the database
    // column name to which the enumerated value will be mapped.
    // The value attribute in @Enumerated can be set to either EnumType.ORDINAL or
    // EnumType.STRING. If set to EnumType.ORDINAL, the enumerated value will be
    // mapped to an integer column type, where the index of each enumerated value
    // starts at 0. If set to EnumType.STRING, the enumerated value will be mapped
    // to a String column type, where the name of each enumerated value is used as
    // the column value.

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

}
