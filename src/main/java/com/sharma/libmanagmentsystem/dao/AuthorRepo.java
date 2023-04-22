package com.sharma.libmanagmentsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sharma.libmanagmentsystem.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

        // 2 ways of writing custo query
        // jpql- java persistence query language
        // executes query considering java class or object

        // Native Query : considering sql tables

        // jpql
        // @Query(value = "select * from author where email= :emailParam")
        // @Query("select a from author a where a.email= :email") //

        // // @Query(value = "select * from author where email= :?1") // based on the
        // // parmans
        // Author findAuthor(@Param("email") String email);

        @Query("SELECT a FROM Author a WHERE a.name = :name")
        Author findAuthor(@Param("name") String name);

        // Native Query
        @Query(value = "select * from Author where email=: ?1 and name=:?2", nativeQuery = true)
        Author findAuthorOrEmail(String emailParma, String name);

}
