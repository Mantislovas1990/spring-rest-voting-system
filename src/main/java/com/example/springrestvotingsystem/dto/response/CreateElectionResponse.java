package com.example.springrestvotingsystem.dto.response;

import com.example.springrestvotingsystem.entities.Election;
import com.example.springrestvotingsystem.entities.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateElectionResponse {


    private Long id;

    private String title;

    private List<Vote> votes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public CreateElectionResponse(Election election) {
        this.id = election.getId();
        this.title = election.getTitle();
        this.created_at = election.getCreated_at();
        this.updated_at = election.getUpdated_at();
    }
}
