package ru.scarlet.company.services;

import ru.scarlet.company.dtos.StorageFile;

import java.util.UUID;

public interface FileService {

    void save(StorageFile fileData, String username);

    void delete(UUID oguid, String deletedBy);
}
