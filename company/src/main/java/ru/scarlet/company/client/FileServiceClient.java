package ru.scarlet.company.client;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.scarlet.company.dtos.StorageFile;

import java.util.UUID;

@FeignClient(name = "file-service", url = "localhost:8082/api/v1/files", dismiss404 = true)
public interface FileServiceClient {
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<StorageFile> addFile(@Param("file") MultipartFile multipartFile, @RequestParam String courseId);

    @GetMapping("/{oguid}")
    ResponseEntity<Resource> getFile(@PathVariable UUID oguid, @RequestParam String courseId);

    @DeleteMapping("/{oguid}")
    ResponseEntity<Void> deleteFile(@PathVariable UUID oguid, @RequestParam String courseId);
}
