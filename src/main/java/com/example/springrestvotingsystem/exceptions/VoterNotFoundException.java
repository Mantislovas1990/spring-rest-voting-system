package com.example.springrestvotingsystem.exceptions;

import lombok.Getter;

@Getter
public class VoterNotFoundException extends RuntimeException {

    private final String username;

    public VoterNotFoundException(String username) {
        this.username = username;
    }
}
