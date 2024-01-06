package ru.scarlet.company.services;

import ru.scarlet.company.dtos.StorageFile;
import ru.scarlet.company.entities.FileData;
import ru.scarlet.company.entities.FileLink;

import java.util.UUID;

public interface FileService {

    void save(StorageFile fileData, String username);

    void delete(UUID oguid, String deletedBy);

    FileData getById(UUID oguid);

    String createLink(UUID oguid);

    FileLink saveLink(String id, Long ttl, UUID oguid, String username);

    FileLink getLink(String link);
}
