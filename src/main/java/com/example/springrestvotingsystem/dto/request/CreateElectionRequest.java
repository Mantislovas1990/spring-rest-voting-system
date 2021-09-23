package com.example.springrestvotingsystem.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateElectionRequest {

    @NotBlank
    @ApiModelProperty(notes = "title of election", required = true, name = "title", value = "Election2022", example = "Election2022")
    private String title;
}
