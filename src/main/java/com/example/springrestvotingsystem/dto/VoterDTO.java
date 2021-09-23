package com.example.springrestvotingsystem.dto;

import com.example.springrestvotingsystem.entities.Role;
import com.example.springrestvotingsystem.entities.Vote;
import com.example.springrestvotingsystem.entities.Voter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class VoterDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private Integer age;

    private Set<Role> roles;

    private String createdAt;

    private String updatedAt;

    private List<Vote> votes;

    public VoterDTO(Voter voter) {
        this.id = voter.getId();
        this.username = voter.getUsername();
        this.firstName = voter.getFirstName();
        this.lastName = voter.getLastName();
        this.age = voter.getAge();
        this.roles = voter.getRoles();
        this.votes=voter.getVotes();
        this.gender=voter.getGender();
        this.email=voter.getEmail();
        this.createdAt = voter.getCreatedAt().toString();
        this.updatedAt = voter.getUpdatedAt().toString();
    }
}
