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
import ru.scarlet.company.client.AuthClient;
import ru.scarlet.company.client.FileServiceClient;
import ru.scarlet.company.dtos.ErrorDetails;
import ru.scarlet.company.dtos.UsernameFromToken;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.excpetions.BadRequest.BadRequestExceprion;
import ru.scarlet.company.excpetions.Null.HeaderNullException;
import ru.scarlet.company.services.FileService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    @Autowired
    private FileServiceClient fileServiceClient;
    @Autowired
    private AuthClient authClient;

    @Autowired
    private FileService fileService;

    @PostMapping("/")
    ResponseEntity<?> addFile(@RequestParam("file") MultipartFile multipartFile, @RequestHeader String authToken, HttpServletRequest request, @RequestParam String courseId){
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        log.info("addFile ");

        log.info("file addded");
        ResponseEntity<UsernameFromToken> username = authClient.getUsername(authToken);
        if (username.getStatusCode().is2xxSuccessful()) {
            var file = fileServiceClient.addFile(multipartFile, courseId);
            fileService.save(file.getBody(), username.getBody().getUsername(), courseId);
            return file;
        } else return ResponseEntity.badRequest().body(
                new ErrorDetails(Instant.now().toEpochMilli(),
                        request.getRequestURI(), "Not found",
                        400, MDC.get("CorrId")));
    }

    @GetMapping("/{oguid}")
    ResponseEntity<?> getFile(@PathVariable UUID oguid, HttpServletRequest request, @RequestHeader String authToken, @RequestParam String courseId){
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        String username = getUsernameFromToken(authToken);
        FileData fileFromDB = fileService.getById(oguid);
        if (fileFromDB == null)
            return ResponseEntity.badRequest().body(
                    new ErrorDetails(Instant.now().toEpochMilli(),
                            request.getRequestURI(), "Not found",
                            400, MDC.get("CorrId")));
        if (!fileFromDB.getCreatedBy().equals(username))
            return ResponseEntity.badRequest().body(
                    new ErrorDetails(Instant.now().toEpochMilli(),
                            request.getRequestURI(), "FORBIDDEN",
                            403, MDC.get("CorrId")));
        ResponseEntity<Resource> file = fileServiceClient.getFile(oguid, courseId);
        if (file.getStatusCode().is2xxSuccessful())
            return file;
        else return ResponseEntity.badRequest().body(
                new ErrorDetails(Instant.now().toEpochMilli(),
                        request.getRequestURI(), "Not found",
                        400, MDC.get("CorrId")));
    }

    @DeleteMapping("/{oguid}")
    ResponseEntity<?> deleteFile(@PathVariable UUID oguid, HttpServletRequest request, @RequestHeader String authToken, @RequestParam String courseId){
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        ResponseEntity<Void> voidResponseEntity = fileServiceClient.deleteFile(oguid, courseId);
        if (voidResponseEntity.getStatusCode() == HttpStatusCode.valueOf(204)){
            String deletedBy = getUsernameFromToken(authToken);
            fileService.delete(oguid, deletedBy);
            return voidResponseEntity;
        }
        else return ResponseEntity.badRequest().body(
                new ErrorDetails(Instant.now().toEpochMilli(),
                        request.getRequestURI(), "Not found",
                        400, MDC.get("CorrId")));
    }


    @GetMapping("/")
    public ResponseEntity<List<FileData>> getAllFiles(HttpServletRequest request, @RequestHeader String authToken, @RequestParam String courseId){
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        String username = getUsernameFromToken(authToken);
        return ResponseEntity.ok(fileService.getAllFiles(username));
    }
    private String getUsernameFromToken(String token){
        ResponseEntity<UsernameFromToken> response = authClient.getUsername(token);
        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody().getUsername();
        }
        else throw new BadRequestExceprion();
    }
}
