package ru.scarlet.company.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.scarlet.company.client.FileServiceClient;
import ru.scarlet.company.services.FileService;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    @Autowired
    private FileServiceClient fileServiceClient;

    @Autowired
    private FileService fileService;

    @PostMapping("/")
    ResponseEntity<?> addFile(@RequestParam("file") MultipartFile multipartFile){
        log.info("addFile ");
        var file = fileServiceClient.addFile(multipartFile);
        log.info("file addded");
        fileService.save(file.getBody());
        return file;
    }

}
