package com.example.springrestvotingsystem.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final Long id;

    public ResourceNotFoundException(Long id) {
        this.id = id;
    }
}
