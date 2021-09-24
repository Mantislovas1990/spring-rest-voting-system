package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.request.CreateVoteRequest;
import com.example.springrestvotingsystem.entities.Vote;
import com.example.springrestvotingsystem.entities.Voter;
import com.example.springrestvotingsystem.services.VoteService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
