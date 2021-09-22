package com.example.springrestvotingsystem.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String username;

    @Size(min = 6, max = 30)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Range(min = 1, max = 140)
    private Integer age;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String gender;
}
