package ru.scarlet.fileservice.service.impl

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.util.MimeType
import org.springframework.web.multipart.MultipartFile
import ru.scarlet.fileservice.models.StorageFile
import ru.scarlet.fileservice.service.FilesService
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors


@Service
class FilesServiceImpl : FilesService {
    override fun addFile(multipartFile: MultipartFile, courseId: String?): StorageFile {
        val storageFile: StorageFile = getStorageFile(multipartFile, courseId)

        val path = storageFile.actualFilePath

        multipartFile.inputStream.use { inputStream ->
            FileOutputStream(path.toString()).use { os ->
                val buffer = ByteArray(2048)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    os.write(buffer, 0, bytesRead)
                }
                os.flush()
                return if (Files.exists(path)) {
                    storageFile
                } else {
                    throw IOException("The file should have been created but was not found. $path")
                }
            }
        }
    }

    override fun loadFile(oguid: UUID, courseId: String?): Resource {
        val storageFile = StorageFile()
        storageFile.id = oguid
        val arrayPath = System.getProperty("user.home") + calcStoragePath(oguid, courseId)
        storageFile.diskArray = arrayPath
        storageFile.path = formPath(oguid)
        val fileName = getFileInDir(storageFile.actualDirectoryPath)
        val filePath = Paths.get(fileName)

        val resource = FileSystemResource(filePath.toAbsolutePath())

        if (resource.exists()) return resource
        else throw Exception("File not found ${filePath.toAbsolutePath()}")
    }

    override fun deleteFile(oguid: UUID, courseId: String?) {
        val path = getFilePath(oguid, courseId)
            Files.delete(path)
    }

    private fun getFilePath(oguid: UUID, courseId: String?): Path {
        val storageFile = StorageFile()
        storageFile.id = oguid
        val arrayPath = System.getProperty("user.home") + calcStoragePath(storageFile.id, courseId)
        storageFile.diskArray = arrayPath
        storageFile.path = formPath(oguid)
        val fileName = getFileInDir(storageFile.actualDirectoryPath)

        return Paths.get(fileName)
    }

    private fun getFileInDir(dirPath: Path): String {
        try {
            Files.walk(dirPath).use { walk ->
                val result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList())
                result.forEach(Consumer { s: String -> println(" ==>> $s") })
                if (result.size > 0) {
                    return result[0]
                }
            }
        } catch (e: NoSuchFileException) {
            println("no file found")
            println(e.message)
        } catch (e: IOException){
            println("excetion IOException")
            println(e.message)
        }
        throw IOException("FILE NOT FOUND") //fixme for FileMotFoundException
    }

    private fun getStorageFile(multipartFile: MultipartFile?, courseId: String?): StorageFile {
        if (multipartFile == null) {
            throw IllegalArgumentException("Must not be null")
        }
        val storageFile: StorageFile =
            prepareFile(multipartFile.originalFilename, MimeType.valueOf(multipartFile.contentType))

        val arrayPath = System.getProperty("user.home") + calcStoragePath(storageFile.id, courseId)
        storageFile.diskArray = arrayPath
        storageFile.size = multipartFile.size

        val path = storageFile.actualFilePath
        if (!Files.exists(path))  {
                Files.createDirectories(storageFile.actualDirectoryPath)

        }
        return storageFile
    }

    private fun calcStoragePath(id: UUID?, courseId: String?=null) : String {
        return if (id!= null) StringBuilder(15).append("/files").append("/course-$courseId").toString() else throw Exception("Something went wrong")
    }

    private fun prepareFile(filename: String?, mime: MimeType?): StorageFile {
        val file = StorageFile()

            file.id = UUID.randomUUID()
                file.apply {
            path = formPath(id!!)
            name = filename
            mimeType = mime.toString()
        }

        return file
    }

    private fun formPath(id: UUID): String {
        return "/" + java.lang.String.join("/", id.toString().replace("-", "").split("(?<=\\G.{2})".toRegex()))
    }
}