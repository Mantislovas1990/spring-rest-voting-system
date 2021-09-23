package com.example.springrestvotingsystem.controlles;

import com.example.springrestvotingsystem.services.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    //DB APPROACH TAKEN
    @PostMapping("/images/blob")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "store file to db", tags = "uploadBlobImage", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully stored file to DB"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public void uploadBlobImage(@RequestParam("image") MultipartFile image) {
        fileService.uploadBlobImage(image);
    }

    @ApiOperation(value = "Get file from db by file name", tags = "getBlobImage", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get file"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping(value = "/images/blob/{filename}", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getBlobImage(@PathVariable("filename") String filename) {
        return fileService.getBlobImage(filename);
    }
}