package ru.scarlet.company.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.scarlet.company.client.FileServiceClient;
import ru.scarlet.company.services.FileService;

import java.util.UUID;

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

    @GetMapping("/{oguid}")
    ResponseEntity<?> getFile(@PathVariable UUID oguid){
        ResponseEntity<Resource> file = fileServiceClient.getFile(oguid);
        if (file.getStatusCode().is2xxSuccessful())
            return file;
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{oguid}")
    ResponseEntity<?> deleteFile(@PathVariable UUID oguid){
        ResponseEntity<Void> voidResponseEntity = fileServiceClient.deleteFile(oguid);
        if (voidResponseEntity.getStatusCode() == HttpStatusCode.valueOf(204)){
            return voidResponseEntity;
        }
        else return ResponseEntity.notFound().build();
    }

}
