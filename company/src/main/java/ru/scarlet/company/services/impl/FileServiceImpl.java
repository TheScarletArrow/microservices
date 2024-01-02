package ru.scarlet.company.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scarlet.company.dtos.StorageFile;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.repository.FileDataRepository;
import ru.scarlet.company.services.FileService;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    FileDataRepository fileDataRepository;


    @Override
    @Transactional
    public void save(StorageFile storageFile, String username) {
        log.info("save file");
        FileData fileData = new FileData();
        fileData.setFileName(storageFile.getName());
        fileData.setFileUUID(storageFile.getId());
        fileData.setCreated(Instant.now());
        fileData.setPath(storageFile.getPath());
        fileData.setSize(storageFile.getSize());
        fileData.setMimeType(storageFile.getMimeType());
        fileData.setDiskArray(storageFile.getDiskArray());
        fileData.setIsDeleted(false);
        fileData.setCreatedBy(username);
        fileDataRepository.save(fileData);
    }

    @Override
    @Transactional
    public void delete(UUID oguid, String deletedBy) {
        log.info("delete {}", oguid);
        FileData fileData = fileDataRepository.findByFileUUID(oguid);
        fileData.setDeletedBy(deletedBy);
        fileData.setDeletedAt(Instant.now());
        fileData.setIsDeleted(true);
    }

    @Override
    @Transactional
    public FileData getById(UUID oguid) {
        return fileDataRepository.findByFileUUID(oguid);
    }
}
