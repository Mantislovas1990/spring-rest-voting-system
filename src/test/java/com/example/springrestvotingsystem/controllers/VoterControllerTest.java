package com.example.springrestvotingsystem.controllers;

import com.example.springrestvotingsystem.repositories.VoterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WithUserDetails("admin")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VoterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VoterRepository voterRepository;

//    @Test
//    public void createVoterTest() throws Exception {
//
//        RegisterRequest registerRequest = new RegisterRequest("user1", "password", "name", "lastname", 22, "email1", "female");
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register")
//                .content(objectMapper.writeValueAsString(registerRequest))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andReturn();
//
//        VoterDTO voterDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), VoterDTO.class);
//
//        assertEquals(registerRequest.getUsername(),voterDTO.getUsername());
//    }


}
