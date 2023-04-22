package com.sharma.libmanagmentsystem.requests;

import com.sharma.libmanagmentsystem.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class UserCreateRequest {

    @Positive
    private int age;

    private String name;

    private String phno;

    @NotBlank
    @Email
    private String email;

    public User to() {
        return User.builder().name(name).ph(phno).email(email).build();
    }
}
