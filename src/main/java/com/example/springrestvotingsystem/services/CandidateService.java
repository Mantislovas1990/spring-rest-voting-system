package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.exceptions.ResourceNotFoundException;
import com.example.springrestvotingsystem.repositories.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        updatedCandidate.setCreated_at(candidate.getCreated_at());

        return candidateRepository.save(updatedCandidate);
    }

    public Candidate createCandidate(Candidate candidate, MultipartFile img) {

        fileService.uploadBlobImage(img);
        return candidateRepository.save(candidate);
    }
}