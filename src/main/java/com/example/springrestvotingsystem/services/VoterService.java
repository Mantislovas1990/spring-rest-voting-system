package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.entities.Voter;
import com.example.springrestvotingsystem.exceptions.ResourceNotFoundException;
import com.example.springrestvotingsystem.exceptions.VoterAlreadyExistsException;
import com.example.springrestvotingsystem.repositories.RoleRepository;
import com.example.springrestvotingsystem.repositories.VoterRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class VoterService implements UserDetailsService {

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final VoterRepository voterRepository;

    public VoterService(RoleRepository roleRepository, PasswordEncoder passwordEncoder, VoterRepository voterRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.voterRepository = voterRepository;
    }

    public List<Voter> getVoters() {
        return voterRepository.findAll();
    }

    public Voter getVoter(Long id) {
        return voterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Voter createVoter(Voter voter) {
        if (voterRepository.findVoterByUsername(voter.getUsername()).isPresent()) {
            throw new VoterAlreadyExistsException(voter.getUsername());
        } else {
            voter.setRoles(Set.of(roleRepository.findRoleByName("USER").get()));
        }

        voter.setPassword(passwordEncoder.encode(voter.getPassword()));

        return voterRepository.save(voter);
    }

    public void deleteVoter(Long id) {
        voterRepository.deleteById(getVoter(id).getId());
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return null;
    }
}
