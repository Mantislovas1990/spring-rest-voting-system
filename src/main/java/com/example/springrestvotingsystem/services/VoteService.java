package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.entities.Vote;
import com.example.springrestvotingsystem.entities.Voter;
import com.example.springrestvotingsystem.repositories.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final CandidateService candidateService;
    private final ElectionService electionService;

    public VoteService(VoteRepository voteRepository, CandidateService candidateService, ElectionService electionService) {
        this.voteRepository = voteRepository;
        this.candidateService = candidateService;
        this.electionService = electionService;
    }

    public Vote addVote(Long candidateId, Long electionId, Voter voter) {
        Vote vote = new Vote(candidateService.getCandidate(candidateId), voter, electionService.getElection(electionId));

        return voteRepository.save(vote);
    }
}
