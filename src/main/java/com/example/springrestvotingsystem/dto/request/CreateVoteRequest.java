package com.example.springrestvotingsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoteRequest {

   private Long candidateId;

   private Long electionId;


}
