package com.newlight77.core.storage

import org.apache.commons.io.IOUtils
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.CREATE_NEW
import java.nio.file.StandardOpenOption.WRITE

class FileStorage {

    fun save(inputStream: InputStream, target: Path): Long {
        inputStream.use {
            if (!Files.isDirectory(target.parent))
                Files.createDirectories(target.parent)

            val tempFile = Paths.get("$target.tmp")
            Files.deleteIfExists(tempFile)

            var byteCount: Long = 0
            Files.newOutputStream(tempFile, CREATE_NEW, WRITE).use { outputStream ->
                try {
                    byteCount = IOUtils.copyLarge(inputStream, outputStream)
                } catch (e: IOException) {
                    Files.deleteIfExists(tempFile)
                }
            }

            Files.move(tempFile, target)

            return byteCount
        }
    }

}


