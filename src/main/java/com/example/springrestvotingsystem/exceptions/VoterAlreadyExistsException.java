package com.example.springrestvotingsystem.exceptions;

import lombok.Getter;

@Getter
public class VoterAlreadyExistsException extends RuntimeException{

    private final String username;

    public VoterAlreadyExistsException(String username) {
        this.username = username;
    }
}
