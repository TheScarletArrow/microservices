package ru.scarlet.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@RequiredArgsConstructor
@Data
public class FileLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;

    @Column(nullable = false)
    private UUID fileOguid;

    private Instant createdDate = Instant.now();

    private String createdBy;

    private Instant validTill;

    private Boolean isValid;

    private Boolean isDeleted = false;

    private Boolean isExpired;
}
