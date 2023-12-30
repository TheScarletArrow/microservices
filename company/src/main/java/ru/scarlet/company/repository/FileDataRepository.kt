package ru.scarlet.company.repository;

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.company.entities.FileData

interface FileDataRepository : JpaRepository<FileData, Long> {
}