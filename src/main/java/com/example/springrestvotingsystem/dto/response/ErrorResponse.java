package com.example.springrestvotingsystem.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private int status;

    private Error code;

    private String message;

    public ErrorResponse(String message, Error code, int status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
