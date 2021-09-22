package com.example.springrestvotingsystem.exceptions;


import lombok.Getter;

@Getter
public class FileNotFoundException extends RuntimeException {

    private final String filename;

    public FileNotFoundException(String filename) {
        this.filename = filename;
    }
}
