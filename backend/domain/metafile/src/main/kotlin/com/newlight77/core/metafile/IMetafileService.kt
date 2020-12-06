package com.newlight77.core.metafile

import com.newlight77.core.metafile.MetafileDomain
import java.io.InputStream
import java.util.*

interface IMetafileService {
    fun save(metafile: MetafileDomain, inpputStream: InputStream)
    fun findById(id: Long): Optional<MetafileDomain>
    fun findByFilename(filename: String): List<MetafileDomain>
    fun findByUsername(username: String): List<MetafileDomain>
}