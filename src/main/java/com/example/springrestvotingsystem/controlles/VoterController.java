package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.VoterDTO;
import com.example.springrestvotingsystem.services.VoterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/voters")
@Api(tags = "VoterController")
public class VoterController {

    private final VoterService voterService;


    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @ApiOperation(value = "Get all voters", tags = "getVoters", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of voters"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<VoterDTO> getVoters(){
        return voterService.getVoters().stream().map(VoterDTO::new).collect(toList());
    }

    @ApiOperation(value = "Get voter by id", tags = "getVoter", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get voter by id"),
            @ApiResponse(code = 404, message = "Voter not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public VoterDTO getVoter(@PathVariable("id") Long id) {
        return new VoterDTO(voterService.getVoter(id));
    }

    @ApiOperation(value = "Delete voter by id", tags = "deleteVoter", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete voter by id"),
            @ApiResponse(code = 404, message = "Voter not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVoter(@PathVariable("id") Long id) {
        voterService.deleteVoter(id);
    }


}
