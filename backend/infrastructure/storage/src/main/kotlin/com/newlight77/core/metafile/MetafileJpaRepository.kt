package com.newlight77.core.metafile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface MetafileJpaRepository : JpaRepository<MetafileEntity, Long> {
    fun save(entity: MetafileEntity): MetafileEntity
    fun findById(username: String): List<MetafileEntity>
    @Query("SELECT t FROM MetafileEntity t where t.username like %:username%")
    fun findByUsername(username: String): List<MetafileEntity>
    @Query("SELECT t FROM MetafileEntity t where t.filename like %:filename%")
    fun findByFilename(filename: String): List<MetafileEntity>

}
