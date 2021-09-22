package com.example.springrestvotingsystem.dto.response;


import com.example.springrestvotingsystem.dto.VoterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private VoterDTO voterDTO;
}
