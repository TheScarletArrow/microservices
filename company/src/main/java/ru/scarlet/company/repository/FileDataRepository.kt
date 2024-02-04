package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.company.entities.FileData
import java.util.*

interface FileDataRepository : JpaRepository<FileData, Long> {


    fun findByFileUUID(fileUUID: UUID): FileData?


    fun findByCreatedBy(createdBy: String): List<FileData>?
}