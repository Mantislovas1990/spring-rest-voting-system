package com.example.springrestvotingsystem.repositories;


import com.example.springrestvotingsystem.entities.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {


    //TODO CHECK ME IF IM WORKING CORRECTLY!!
    List<Election> getElectionByTitleContainingIgnoreCase(String text);
}
