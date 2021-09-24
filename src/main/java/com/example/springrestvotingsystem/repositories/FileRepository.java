package com.example.springrestvotingsystem.repositories;

import com.example.springrestvotingsystem.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByFilename(String filename);

}
