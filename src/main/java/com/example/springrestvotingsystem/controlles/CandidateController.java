package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.dto.CandidateDTO;
import com.example.springrestvotingsystem.dto.request.CreateCandidateRequest;
import com.example.springrestvotingsystem.dto.request.UpdateCandidateRequest;
import com.example.springrestvotingsystem.dto.response.CreateCandidateResponse;
import com.example.springrestvotingsystem.dto.response.UpdateCandidateResponse;
import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.entities.File;
import com.example.springrestvotingsystem.services.CandidateService;
import com.example.springrestvotingsystem.services.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final FileService fileService;


    public CandidateController(CandidateService candidateService, FileService fileService) {
        this.candidateService = candidateService;
        this.fileService = fileService;
    }

    @ApiOperation(value = "Get all candidates", tags = "getCandidates", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of candidates"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    public List<CandidateDTO> getCandidates(@RequestParam(required = false) Boolean desc) {
        if(Boolean.TRUE.equals(desc)){
            return candidateService.showWhoIsWinningNow().stream().map(CandidateDTO::new).collect(toList());
        }
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    @PostMapping(path = "/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCandidateResponse createCandidate(@Valid @RequestBody CreateCandidateRequest createCandidateRequest) {
        return new CreateCandidateResponse(candidateService.createCandidate(new Candidate(createCandidateRequest)));
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

    @PostMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "store candidate image to database", tags = "uploadCandidateImage", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully stored candidate image"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public void uploadCandidateImage(@RequestParam("image") MultipartFile image,
                                     @PathVariable("id") Candidate candidate) {
        fileService.uploadBlobImage(image,candidate);
    }


    @ApiOperation(value = "Get candidate image from database", tags = "getCandidateImage", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get candidate image"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping(value = "/{id}/image", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getCandidateImage(@PathVariable("id") Long id) {
        return candidateService.getCandidate(id).getImage().getBytes();
    }

}
