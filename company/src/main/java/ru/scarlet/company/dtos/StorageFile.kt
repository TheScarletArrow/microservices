package ru.scarlet.company.dtos

import lombok.*
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class StorageFile {
    var id: UUID? = null
     var path: String? = null
     var name: String? = null
     var size = 0L
     var mimeType: String? = null
     var diskArray: String? = null
    val actualFilePath: Path
        get() = Paths.get(actualDirectoryPath.toString(), name)
    val actualDirectoryPath: Path
        get() = Paths.get(diskArray, path)
}
