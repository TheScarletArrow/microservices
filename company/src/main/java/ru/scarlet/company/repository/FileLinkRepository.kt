package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.company.entities.FileLink
import java.util.*

interface FileLinkRepository : JpaRepository<FileLink, Long> {


    fun findByLink(link: String): FileLink?


    fun findByFileOguid(fileOguid: UUID): List<FileLink>
}