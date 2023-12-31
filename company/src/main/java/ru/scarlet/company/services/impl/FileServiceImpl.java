package ru.scarlet.company.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.StorageFile;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.repository.FileDataRepository;
import ru.scarlet.company.services.FileService;

import java.time.Instant;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    FileDataRepository fileDataRepository;


    @Override
    public void save(StorageFile storageFile) {
        log.info("save file");
        FileData fileData = new FileData();
        fileData.setFileName(storageFile.getName());
        fileData.setFileUUID(storageFile.getId());
        fileData.setCreated(Instant.now());
        fileData.setPath(storageFile.getPath());
        fileData.setSize(storageFile.getSize());
        fileData.setMimeType(storageFile.getMimeType());
        fileData.setDiskArray(storageFile.getDiskArray());
        fileDataRepository.save(fileData);
    }
}
