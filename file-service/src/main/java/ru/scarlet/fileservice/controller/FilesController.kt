package ru.scarlet.fileservice.controller

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.scarlet.fileservice.models.StorageFile
import ru.scarlet.fileservice.service.FilesService
import java.io.IOException
import java.net.URLEncoder
import java.util.*
import kotlin.text.Charsets.UTF_8

@RestController
@RequestMapping("/api/v1/files")
class FilesController(private val filesService: FilesService) {

    companion object{
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }
    @PostMapping("/")
    fun addFile(@RequestParam("file") multipartFile: MultipartFile): ResponseEntity<StorageFile> {
        logger.info("file service addfile")
        val file = filesService.addFile(multipartFile)

        return ResponseEntity.ok(file)
    }

    @GetMapping("/{oguid}")
    fun getFile(@PathVariable oguid: UUID, request: HttpServletRequest): ResponseEntity<Resource> {
        val resource: Resource = filesService.loadFile(oguid)

        val contentType = try {
            request.servletContext.getMimeType(resource.file.absolutePath)
        } catch (e: IOException) {
            null
        }

        val contentLength = try {
             resource.file.length()
        } catch (e: IOException) {
            0L
        }

        contentType?:"application/octet-stream"

        val uriFileName = URLEncoder.encode(resource.filename, UTF_8)

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .contentLength(contentLength)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"${uriFileName}\"")
            .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, listOf("origin", "x-requested-with", "content-type", "accept-ranges").joinToString(", "))
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, listOf("accept-ranges").joinToString(", "))
            .body(resource)
    }

    @DeleteMapping("/{oguid}")
    fun deleteFile(@PathVariable oguid: UUID):ResponseEntity<Void>{
        filesService.deleteFile(oguid)
        return ResponseEntity.noContent().build()
    }
}