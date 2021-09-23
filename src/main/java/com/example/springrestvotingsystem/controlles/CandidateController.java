package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.CandidateDTO;
import com.example.springrestvotingsystem.dto.request.CreateCandidateRequest;
import com.example.springrestvotingsystem.dto.request.UpdateCandidateRequest;
import com.example.springrestvotingsystem.dto.response.CreateCandidateResponse;
import com.example.springrestvotingsystem.dto.response.UpdateCandidateResponse;
import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.services.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/candidates")
@Api(tags = "CandidateController")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @ApiOperation(value = "Get all candidates", tags = "getCandidates", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of candidates"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    public List<CandidateDTO> getCandidates(@RequestParam(required = false) Boolean desc) {
        return candidateService.getCandidates().stream().map(CandidateDTO::new).collect(toList());
    }

    @ApiOperation(value = "Get single candidate by id", tags = "getCandidate", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get one candidate"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping("/{id}")
    public CandidateDTO getCandidate(@PathVariable("id") Long id) {
        return new CandidateDTO(candidateService.getCandidate(id));
    }

    @ApiOperation(value = "Delete candidate by id", tags = "deleteCandidate", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted candidate by id"),
            @ApiResponse(code = 404, message = "Candidate not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCandidate(@PathVariable("id") Long id) {
        candidateService.deleteCandidate(id);
    }

    @ApiOperation(value = "Update candidate", tags = "updateCandidate", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully create candidate record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateCandidateResponse updateCandidate(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateCandidateRequest updateCandidateRequest) {
        return new UpdateCandidateResponse(candidateService.updateCandidate(new Candidate(id, updateCandidateRequest)));
    }

    @ApiOperation(value = "Create candidate", tags = "createCandidate", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully create candidate record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CreateCandidateResponse createCandidate(@Valid @RequestBody CreateCandidateRequest createCandidateRequest,
                                                   @RequestParam("img") MultipartFile file) {
        return new CreateCandidateResponse(candidateService.createCandidate(new Candidate(createCandidateRequest),file));
    }

    @ApiOperation(value = "Get winner candidate", tags = "getWinnerOfElection", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get winner candidate"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping("/winner")
    public Optional<Candidate> getWinnerOfElection() {
        return candidateService.findWhoWonElection();
    }


}
