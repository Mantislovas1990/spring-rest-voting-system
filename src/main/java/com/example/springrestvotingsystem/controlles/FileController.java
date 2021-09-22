package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

//    @PostMapping("/images")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void uploadImage(@RequestParam("image") MultipartFile image) {
//        fileService.uploadImage(image);
//    }
//
//    @GetMapping(value = "/images/{filename}", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
//    public byte[] getImage(@PathVariable("filename") String filename) {
//        return fileService.getImage(filename);
//    }


    //DB APPROACH TAKEN
    @PostMapping("/images/blob")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadBlobImage(@RequestParam("image") MultipartFile image) {
        fileService.uploadBlobImage(image);
    }

    @GetMapping(value = "/images/blob/{filename}", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getBlobImage(@PathVariable("filename") String filename) {
        return fileService.getBlobImage(filename);
    }
}