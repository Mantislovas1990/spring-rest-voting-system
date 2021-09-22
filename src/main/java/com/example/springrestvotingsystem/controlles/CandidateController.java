package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.CandidateDTO;
import com.example.springrestvotingsystem.dto.request.CreateCandidateRequest;
import com.example.springrestvotingsystem.dto.request.UpdateCandidateRequest;
import com.example.springrestvotingsystem.dto.response.CreateCandidateResponse;
import com.example.springrestvotingsystem.dto.response.UpdateCandidateResponse;
import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.services.CandidateService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/candidates")
@Api(tags = "CandidateController")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateDTO> getCandidates() {
        return candidateService.getCandidates().stream().map(CandidateDTO::new).collect(toList());
    }

    @GetMapping("/{id}")
    public CandidateDTO getCandidate(@PathVariable("id") Long id) {
        return new CandidateDTO(candidateService.getCandidate(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCandidate(@PathVariable("id") Long id) {
        candidateService.deleteCandidate(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateCandidateResponse updateCandidate(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UpdateCandidateRequest updateCandidateRequest) {
        return new UpdateCandidateResponse(candidateService.updateCandidate(new Candidate(id, updateCandidateRequest)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CreateCandidateResponse createCandidate(@Valid @RequestBody CreateCandidateRequest createCandidateRequest,
                                                   @RequestParam("img") MultipartFile file) {
        return new CreateCandidateResponse(candidateService.createCandidate(new Candidate(createCandidateRequest),file));
    }

}
