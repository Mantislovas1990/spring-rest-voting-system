package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.ElectionDTO;
import com.example.springrestvotingsystem.dto.request.CreateElectionRequest;
import com.example.springrestvotingsystem.dto.response.CreateElectionResponse;
import com.example.springrestvotingsystem.entities.Election;
import com.example.springrestvotingsystem.services.ElectionService;
import io.swagger.annotations.Api;
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

    @GetMapping
    public List<ElectionDTO> getElections() {
        return electionService.getElections().stream().map(ElectionDTO::new).collect(toList());
    }

    @GetMapping("/{id}")
    public ElectionDTO getElection(@PathVariable("id") Long id) {
        return new ElectionDTO(electionService.getElection(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CreateElectionResponse createElection(@Valid @RequestBody CreateElectionRequest createElectionRequest) {
        return new CreateElectionResponse(electionService.createElection(new Election(createElectionRequest)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteElection(@PathVariable("id") Long id) {
        electionService.deleteElection(id);
    }

}
