package ru.scarlet.company.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.company.client.AuthClient;
import ru.scarlet.company.client.FileServiceClient;
import ru.scarlet.company.dtos.ErrorDetails;
import ru.scarlet.company.dtos.UsernameFromToken;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.entities.FileLink;
import ru.scarlet.company.excpetions.BadRequest.BadRequestExceprion;
import ru.scarlet.company.excpetions.Null.HeaderNullException;
import ru.scarlet.company.services.FileService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Slf4j
public class LinkController {
    @Autowired
    private FileServiceClient fileServiceClient;
    @Autowired
    private AuthClient authClient;

    @Autowired
    private FileService fileService;

    @PostMapping("files/{oguid}/link")
    public ResponseEntity<?> createLink(@PathVariable UUID oguid, HttpServletRequest request, @RequestHeader String authToken, @RequestParam(defaultValue = "86400") Long ttl){
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        String username = getUsernameFromToken(authToken);
        FileData fileFromDB = fileService.getById(oguid);
        if (!fileFromDB.getCreatedBy().equals(username))
            return ResponseEntity.badRequest().body(
                    new ErrorDetails(Instant.now().toEpochMilli(),
                            request.getRequestURI(), "FORBIDDEN",
                            403, MDC.get("CorrId")));
        String id = fileService.createLink(oguid);
        FileLink fileLink = fileService.saveLink(id, ttl, oguid, username);

        return ResponseEntity.ok().body("api/v1/links/"+fileLink.getLink());
    }

    private String getUsernameFromToken(String token){
        ResponseEntity<UsernameFromToken> response = authClient.getUsername(token);
        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody().getUsername();
        }
        else throw new BadRequestExceprion();
    }

    @GetMapping("/links/{link}")
    public ResponseEntity<?> getLink(@PathVariable String link, HttpServletRequest request, @RequestHeader String authToken, @RequestParam String courseId){
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        String username = getUsernameFromToken(authToken);
        FileLink fileLink = fileService.getLink(link);
        if (fileLink == null)
            return ResponseEntity.badRequest().body(
                    new ErrorDetails(Instant.now().toEpochMilli(),
                            request.getRequestURI(), "Link does not exist ",
                            400, MDC.get("CorrId")));
        if (fileLink.getIsValid()) {
            ResponseEntity<Resource> file = fileServiceClient.getFile(fileLink.getFileOguid(), courseId);
            if (file.getStatusCode().is2xxSuccessful())
                return file;
            else return ResponseEntity.badRequest().body(
                    new ErrorDetails(Instant.now().toEpochMilli(),
                            request.getRequestURI(), "Not found",
                            400, MDC.get("CorrId")));
        }
        return ResponseEntity.badRequest().body(
                new ErrorDetails(Instant.now().toEpochMilli(),
                        request.getRequestURI(), "Link is not valid ",
                        400, MDC.get("CorrId")));
    }

    @GetMapping("/files/{oguid}/links")
    public ResponseEntity<?> getLinks(@PathVariable UUID oguid, HttpServletRequest request, @RequestHeader String authToken) {
        if (authToken == null || authToken.isBlank() || authToken.isEmpty()){
            throw new HeaderNullException("Header authToken is null");
        }
        List<FileLink> fileLinkList = fileService.getLinks(oguid);
        return ResponseEntity.ok().body(fileLinkList);
    }
}
