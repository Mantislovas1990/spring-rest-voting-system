package com.example.springrestvotingsystem.repositories;

import com.example.springrestvotingsystem.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

    Optional<Voter> findVoterByUsername(String username);
}
