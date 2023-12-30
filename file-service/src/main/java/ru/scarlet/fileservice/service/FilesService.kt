package ru.scarlet.fileservice.service

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import ru.scarlet.fileservice.models.StorageFile
import java.util.*

interface FilesService {

    fun addFile(multipartFile: MultipartFile): StorageFile
    fun loadFile(oguid: UUID): Resource
    fun deleteFile(oguid: UUID)
}