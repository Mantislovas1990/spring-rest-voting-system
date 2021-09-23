package com.example.springrestvotingsystem.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateCandidateRequest {

    @NotBlank
    @ApiModelProperty(notes = "Candidate first name", required = true, name = "firstName", value = "Kazys", example = "Kazys")
    private String firstName;

    @NotBlank
    @ApiModelProperty(notes = "Candidate last name", required = true, name = "lastName", value = "Barnauskas", example = "Barnauskas")
    private String lastName;
}
