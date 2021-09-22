package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.services.ElectionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/elections")
@Api(tags = "ElectionController")
public class ElectionController {

    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }



}
