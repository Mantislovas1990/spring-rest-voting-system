package com.example.springrestvotingsystem.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCandidateRequest {

    @NotBlank
    @ApiModelProperty(notes = "Candidate first name", required = true, name = "firstName", value = "Kazys", example = "Kazys")
    private String firstName;

    @NotBlank
    @ApiModelProperty(notes = "Candidate last name", required = true, name = "lastName", value = "Barnauskas", example = "Barnauskas")
    private String lastName;

}
