package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.entities.Candidate;
import com.example.springrestvotingsystem.entities.File;
import com.example.springrestvotingsystem.exceptions.FileException;
import com.example.springrestvotingsystem.exceptions.FileNotFoundException;
import com.example.springrestvotingsystem.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class FileService {

    @Value("${uploads.filesize.max-bytes}")
    private Long maxSize;

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    public void uploadBlobImage(MultipartFile image, Candidate candidate) {
        validateImage(image);

        String filename = String.format("%s_%d.%s", image.getOriginalFilename().split("\\.")[0],
                System.currentTimeMillis(), image.getOriginalFilename().split("\\.")[1]);

        File file = new File();
        file.setFilename(filename);
        file.setSize(image.getSize());

        try {
            file.setBytes(image.getBytes());
        } catch (IOException e) {
            throw new FileException("Cannot create file");
        }


        file.setCandidate(candidate);
        fileRepository.save(file);
    }

    public byte[] getBlobImage(String filename) {
        return fileRepository.findByFilename(filename).orElseThrow(() -> new FileNotFoundException(filename)).getBytes();
    }


    private void validateImage(MultipartFile image) {
        Set<String> allowedTypes = Set.of(MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);

        if (!allowedTypes.contains(image.getContentType())) {
            throw new FileException("File media type is not allowed");
        }

        if (image.getSize() > maxSize) {
            throw new FileException("Image size cannot be more than " + maxSize);
        }
    }

}
