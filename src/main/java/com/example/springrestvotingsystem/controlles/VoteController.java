package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.CandidateDTO;
import com.example.springrestvotingsystem.dto.ElectionDTO;
import com.example.springrestvotingsystem.dto.VoterDTO;
import com.example.springrestvotingsystem.dto.request.CreateCandidateRequest;
import com.example.springrestvotingsystem.dto.request.CreateVoteRequest;
import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.entities.Election;
import com.example.springrestvotingsystem.entities.Vote;
import com.example.springrestvotingsystem.entities.Voter;
import com.example.springrestvotingsystem.services.VoteService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/votes")
@Api(tags = "VoteController")
public class VoteController {


    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PreAuthorize("hasRole('ADMIN') or principal.id == #voter.id")
    @PostMapping()
    public Vote addVote(
            @RequestBody CreateVoteRequest createVoteRequest,
            @AuthenticationPrincipal Voter voter) {

        return voteService.addVote(createVoteRequest.getCandidateId(), createVoteRequest.getElectionId(), voter);
    }

}
