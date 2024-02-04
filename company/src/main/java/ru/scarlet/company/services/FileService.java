package ru.scarlet.company.services;

import ru.scarlet.company.dtos.StorageFile;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.entities.FileLink;

import java.util.List;
import java.util.UUID;

public interface FileService {

    void save(StorageFile fileData, String username, String courseId);

    void delete(UUID oguid, String deletedBy);

    FileData getById(UUID oguid);

    String createLink(UUID oguid);

    FileLink saveLink(String id, Long ttl, UUID oguid, String username);

    FileLink getLink(String link);

    List<FileLink> getLinks(UUID oguid);

    List<FileData> getAllFiles(String username);
}
