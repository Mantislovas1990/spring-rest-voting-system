package com.example.springrestvotingsystem.entities;

import com.example.springrestvotingsystem.dto.CandidateDTO;
import com.example.springrestvotingsystem.dto.request.CreateCandidateRequest;
import com.example.springrestvotingsystem.dto.request.UpdateCandidateRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "candidate")
    private List<Vote> votes;

    @OneToOne(mappedBy = "candidate")
    private File image;

    @CreationTimestamp
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public Candidate(String firstName, String lastName, List<Vote> votes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.votes = votes;
    }

    public Candidate(CreateCandidateRequest createCandidateRequest) {
        this.firstName = createCandidateRequest.getFirstName();
        this.lastName = createCandidateRequest.getLastName();
    }

    public Candidate(Long id,UpdateCandidateRequest updateCandidateRequest){
        this.id = id;
        this.firstName = updateCandidateRequest.getFirstName();
        this.lastName = updateCandidateRequest.getLastName();
    }

    public Candidate(CandidateDTO candidateDTO){
        this.id=candidateDTO.getId();
        this.firstName=candidateDTO.getFirstName();
        this.lastName=candidateDTO.getLastName();
        this.image=candidateDTO.getImage();
        this.createdAt = LocalDateTime.parse(candidateDTO.getCreatedAt());
        this.updatedAt = LocalDateTime.parse(candidateDTO.getUpdatedAt());
    }
}
