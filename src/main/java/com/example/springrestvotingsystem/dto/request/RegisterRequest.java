package com.example.springrestvotingsystem.dto.request;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "user name", required = true, name = "username", value = "testUserName", example = "testUserName")
    private String username;

    @Size(min = 6, max = 30)
    @ApiModelProperty(notes = "password", required = true, name = "password", value = "testPassword", example = "testPassword")
    private String password;

    @NotBlank
    @ApiModelProperty(notes = "Voter first name", required = true, name = "firstName", value = "UserFirstName", example = "UserFirstName")
    private String firstName;

    @NotBlank
    @ApiModelProperty(notes = "Voter last name", required = true, name = "lastName", value = "UserLastName", example = "UserLastName")
    private String lastName;

    @NotNull
    @Range(min = 1, max = 140)
    @ApiModelProperty(notes = "age of voter", required = true, name = "age", value = "22", example = "22")
    private Integer age;

    @Email
    @NotBlank
    @ApiModelProperty(notes = "email of election", required = true, name = "email", value = "testEmail@email.com", example = "testEmail@email.com")
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "gender of election", required = true, name = "gender", value = "female", example = "female")
    private String gender;
}
