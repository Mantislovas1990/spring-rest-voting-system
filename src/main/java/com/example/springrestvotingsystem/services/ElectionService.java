package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.entities.Election;
import com.example.springrestvotingsystem.exceptions.ResourceNotFoundException;
import com.example.springrestvotingsystem.repositories.ElectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionService {

    private final ElectionRepository electionRepository;

    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    public Election getElection(Long id) {
        return electionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Election> getElections() {
        return electionRepository.findAll();
    }

    public void deleteElection(Long id) {
        electionRepository.deleteById(getElection(id).getId());
    }

    public Election createElection(Election election) {

        return electionRepository.save(election);
    }
}
