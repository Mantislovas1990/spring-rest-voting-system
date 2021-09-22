package com.example.springrestvotingsystem.dto;

import com.example.springrestvotingsystem.entities.Role;
import com.example.springrestvotingsystem.entities.Voter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class VoterDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<Role> roles;

    private String created;

    private String updated;

    public VoterDTO(Voter voter) {
        this.id = voter.getId();
        this.username = voter.getUsername();
        this.firstName = voter.getFirstName();
        this.lastName = voter.getLastName();
        this.age = voter.getAge();
        this.roles = voter.getRoles();
        this.created = voter.getCreatedAt().toString();
        this.updated = voter.getUpdatedAt().toString();
    }
}
