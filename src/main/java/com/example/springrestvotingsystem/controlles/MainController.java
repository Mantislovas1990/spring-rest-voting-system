package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.VoterDTO;
import com.example.springrestvotingsystem.dto.request.RegisterRequest;
import com.example.springrestvotingsystem.dto.response.LoginResponse;
import com.example.springrestvotingsystem.entities.Voter;
import com.example.springrestvotingsystem.services.JwtService;
import com.example.springrestvotingsystem.services.VoterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@RestController
public class MainController {

    private final JwtService jwtService;

    private final VoterService voterService;

    public MainController(JwtService jwtService, VoterService voterService) {
        this.jwtService = jwtService;
        this.voterService = voterService;
    }

    @GetMapping("/http")
    public ResponseEntity<Integer> http(@RequestParam("status") Integer status) {
        return new ResponseEntity<>(status, HttpStatus.valueOf(status));
    }


    @ApiOperation(value = "login", tags = "login", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in"),
            @ApiResponse(code = 401, message = "The user is not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 500, message = "Unexpected error")
    })
    @PostMapping("/login")
    public LoginResponse login(@AuthenticationPrincipal Voter voter) {
        return new LoginResponse(jwtService.createToken(voter), new VoterDTO(voter));
    }

    @ApiOperation(value = "Create voter", tags = "register", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created voter"),
            @ApiResponse(code = 500, message = "Unexpected error")
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public VoterDTO register(@Valid @RequestBody RegisterRequest registerRequest) throws RoleNotFoundException {
        return new VoterDTO(voterService.createVoter(
                new Voter(registerRequest)));
    }
}
