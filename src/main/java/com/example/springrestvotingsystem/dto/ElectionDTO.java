package com.example.springrestvotingsystem.dto;

import com.example.springrestvotingsystem.entities.Election;
import com.example.springrestvotingsystem.entities.Vote;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ElectionDTO {

    private Long id;

    private String title;

    private List<Vote> votes;

    public ElectionDTO(Election election) {
        this.id = election.getId();
        this.title = election.getTitle();
        this.votes = election.getVotes();
    }
}
