package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.repositories.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }



}
