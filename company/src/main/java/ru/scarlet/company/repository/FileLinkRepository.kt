package ru.scarlet.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.scarlet.company.entities.FileLink

interface FileLinkRepository : JpaRepository<FileLink, Long> {


    fun findByLink(link: String): FileLink
}