package com.example.springrestvotingsystem.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateCandidateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
