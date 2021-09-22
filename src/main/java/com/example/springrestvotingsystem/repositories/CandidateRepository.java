package com.example.springrestvotingsystem.repositories;

import com.example.springrestvotingsystem.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findTopByOrderByVotesDesc();

    List<Candidate> findAllByOrderByVotesDesc();
}
