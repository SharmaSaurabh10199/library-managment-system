package com.sharma.libmanagmentsystem.requests;

import com.sharma.libmanagmentsystem.model.Author;
import com.sharma.libmanagmentsystem.model.Book;
import com.sharma.libmanagmentsystem.model.Genre;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter // lombok ano for setter
@Getter
@Builder
public class BookCreateRequest {

    @NotBlank
    private String name;

    private int cost;

    @NotNull
    private Genre genre;

    private String authorName;

    @NotBlank
    @Email
    private String email;

    public Book to() {
        Author author = Author.builder().name(authorName).email(email).build();
        return Book.builder().bookName(name).genre(genre).author(author).build();
    }

}
