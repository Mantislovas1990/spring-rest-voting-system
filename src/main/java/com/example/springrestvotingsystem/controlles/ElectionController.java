package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.ElectionDTO;
import com.example.springrestvotingsystem.dto.request.CreateElectionRequest;
import com.example.springrestvotingsystem.dto.response.CreateElectionResponse;
import com.example.springrestvotingsystem.entities.Election;
import com.example.springrestvotingsystem.services.ElectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/elections")
@Api(tags = "ElectionController")
public class ElectionController {

    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @ApiOperation(value = "Get all elections", tags = "getElections", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of election"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    public List<ElectionDTO> getElections() {
        return electionService.getElections().stream().map(ElectionDTO::new).collect(toList());
    }

    @ApiOperation(value = "Get single election by id", tags = "getElections", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get single election by id"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ElectionDTO getElection(@PathVariable("id") Long id) {
        return new ElectionDTO(electionService.getElection(id));
    }


    @ApiOperation(value = "Create election", tags = "createElection", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully create election record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CreateElectionResponse createElection(@Valid @RequestBody CreateElectionRequest createElectionRequest) {
        return new CreateElectionResponse(electionService.createElection(new Election(createElectionRequest)));
    }

    @ApiOperation(value = "Delete election by id", tags = "deleteElection", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully delete election by id"),
            @ApiResponse(code = 404, message = "election not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteElection(@PathVariable("id") Long id) {
        electionService.deleteElection(id);
    }

}
