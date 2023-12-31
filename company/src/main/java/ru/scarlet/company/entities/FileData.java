package ru.scarlet.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    private UUID fileUUID;

    private Instant created;

    private String createdBy;

    private Long size;

    private String fileName;

    private String path;

    private String diskArray;

    private String mimeType;

    private Boolean isDeleted;

    private Instant deletedAt;

    private String deletedBy;
}
