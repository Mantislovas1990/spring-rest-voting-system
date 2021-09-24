package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.exceptions.ResourceNotFoundException;
import com.example.springrestvotingsystem.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final FileService fileService;

    public CandidateService(CandidateRepository candidateRepository, FileService fileService) {
        this.candidateRepository = candidateRepository;
        this.fileService = fileService;
    }

    public Candidate getCandidate(Long id) {
        return candidateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(getCandidate(id).getId());
    }

    public Candidate updateCandidate(Candidate updatedCandidate) {

        Candidate candidate = getCandidate(updatedCandidate.getId());
        updatedCandidate.setCreatedAt(candidate.getCreatedAt());

        return candidateRepository.save(updatedCandidate);
    }

    public Candidate createCandidate(Candidate candidate) {

        return candidateRepository.save(candidate);
    }

    public Optional<Candidate> findWhoWonElection(){
        return candidateRepository.findTopByOrderByVotesDesc();
    }

    public List<Candidate> showWhoIsWinningNow(){
        return candidateRepository.findAllByOrderByVotesDesc();
    }
}
