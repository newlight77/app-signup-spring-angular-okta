package io.github.newlight77.bootstrap.note.port

import java.util.*

interface INotesRepository<T, U> {
    fun save(note: T)
    fun update(note: T)
    fun delete(id: U)
    fun findAll(): List<T>
    fun findById(id: U): Optional<T>
    fun findByUser(user: String): List<T>
    fun findByTitle(title: String): List<T>
}