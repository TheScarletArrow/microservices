package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.scarlet.company.client.FileServiceClient;
import ru.scarlet.company.dtos.ErrorDetails;
import ru.scarlet.company.services.FileService;

import java.time.Instant;
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
    ResponseEntity<?> getFile(@PathVariable UUID oguid, HttpServletRequest request){
        ResponseEntity<Resource> file = fileServiceClient.getFile(oguid);
        if (file.getStatusCode().is2xxSuccessful())
            return file;
        else return ResponseEntity.badRequest().body(
                new ErrorDetails(Instant.now().toEpochMilli(),
                        request.getRequestURI(), "Not found",
                        400, MDC.get("CorrId")));
    }

    @DeleteMapping("/{oguid}")
    ResponseEntity<?> deleteFile(@PathVariable UUID oguid, HttpServletRequest request){
        ResponseEntity<Void> voidResponseEntity = fileServiceClient.deleteFile(oguid);
        if (voidResponseEntity.getStatusCode() == HttpStatusCode.valueOf(204)){
            return voidResponseEntity;
        }
        else return ResponseEntity.badRequest().body(
                new ErrorDetails(Instant.now().toEpochMilli(),
                        request.getRequestURI(), "Not found",
                        400, MDC.get("CorrId")));    }

}
