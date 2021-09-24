package com.example.springrestvotingsystem.controllers;

import com.example.springrestvotingsystem.dto.CandidateDTO;
import com.example.springrestvotingsystem.dto.request.CreateCandidateRequest;
import com.example.springrestvotingsystem.repositories.CandidateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@WithUserDetails("admin")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void createElectionTest() throws Exception {

        CreateCandidateRequest createCandidateRequest = new CreateCandidateRequest("Testas1","testas1");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/candidates/create")
                .content(objectMapper.writeValueAsString(createCandidateRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        CandidateDTO candidateDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CandidateDTO.class);

        assertEquals(createCandidateRequest.getFirstName(),candidateDTO.getFirstName());
    }
}
