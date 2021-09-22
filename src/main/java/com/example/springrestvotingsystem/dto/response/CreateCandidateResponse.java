package com.example.springrestvotingsystem.dto.response;

import com.example.springrestvotingsystem.entities.Candidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateCandidateResponse {

    private Long id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public CreateCandidateResponse(Candidate candidate) {
        this.id = candidate.getId();
        this.firstName = candidate.getFirstName();
        this.lastName = candidate.getLastName();
        this.created_at = candidate.getCreated_at();
        this.updated_at = candidate.getUpdated_at();
    }
}
