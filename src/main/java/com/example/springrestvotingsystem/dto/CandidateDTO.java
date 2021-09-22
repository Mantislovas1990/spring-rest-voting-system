package com.example.springrestvotingsystem.dto;


import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.entities.Vote;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CandidateDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private List<Vote> votes;

    public CandidateDTO(Candidate candidate) {
        this.id = candidate.getId();
        this.firstName = candidate.getFirstName();
        this.lastName = candidate.getLastName();
        this.votes = candidate.getVotes();
    }
}
