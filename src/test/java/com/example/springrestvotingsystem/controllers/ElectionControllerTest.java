package com.example.springrestvotingsystem.controllers;

import com.example.springrestvotingsystem.dto.ElectionDTO;
import com.example.springrestvotingsystem.dto.VoterDTO;
import com.example.springrestvotingsystem.dto.request.CreateElectionRequest;
import com.example.springrestvotingsystem.dto.request.RegisterRequest;
import com.example.springrestvotingsystem.dto.response.CreateElectionResponse;
import com.example.springrestvotingsystem.repositories.ElectionRepository;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

@AutoConfigureMockMvc
@WithUserDetails("admin")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ElectionRepository electionRepository;


    @Test
    public void createElectionTest() throws Exception {

        CreateElectionRequest createElectionRequest = new CreateElectionRequest("Election2022");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/elections/create")
                .content(objectMapper.writeValueAsString(createElectionRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        ElectionDTO electionDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ElectionDTO.class);

        assertEquals(createElectionRequest.getTitle(),electionDTO.getTitle());
    }

    @Test
    public void testDeleteElectionWhenElectionExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/elections/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        assertFalse(electionRepository.existsById(1L));
    }


}
