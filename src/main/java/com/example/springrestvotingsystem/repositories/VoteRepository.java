package com.example.springrestvotingsystem.repositories;

import com.example.springrestvotingsystem.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findBy(Long aLong);
}
