package ru.scarlet.company.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scarlet.company.constants.Constants;
import ru.scarlet.company.dtos.StorageFile;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.entities.FileLink;
import ru.scarlet.company.repository.FileDataRepository;
import ru.scarlet.company.repository.FileLinkRepository;
import ru.scarlet.company.services.FileService;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    FileDataRepository fileDataRepository;

    @Autowired
    FileLinkRepository fileLinkRepository;


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

    @Override
    public String createLink(UUID oguid) {
        String s = Constants.Companion.randomString();
        return s;
    }

    @Override
    public FileLink saveLink(String id, Long ttl, UUID oguid, String username) {
        FileLink fileLink = new FileLink();
        fileLink.setLink(id);
        fileLink.setCreatedBy(username);
        fileLink.setFileOguid(oguid);
        fileLink.setValidTill(Instant.now().plusSeconds(ttl));
        fileLink.setIsValid(true);
        fileLink.setIsExpired(fileLink.getCreatedDate().plusSeconds(ttl).isBefore(Instant.now()));
        return fileLinkRepository.save(fileLink);
    }

    @Override
    @Transactional
    public FileLink getLink(String link) {
        return fileLinkRepository.findByLink(link);
    }
}
