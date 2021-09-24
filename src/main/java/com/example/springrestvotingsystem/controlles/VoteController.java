package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.services.VoteService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vote")
@Api(tags = "VoteController")
public class VoteController {


    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


}
