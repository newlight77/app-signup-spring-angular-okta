package io.github.newlight77.bootstrap.note.service

import io.github.newlight77.bootstrap.note.port.INotesRepository
import io.github.newlight77.bootstrap.note.model.NoteDomain
import java.util.*

class NoteService(private var noteRepository: INotesRepository<NoteDomain, Long>) : INotesService<NoteDomain, Long> {
    override fun save(note: NoteDomain) {
        noteRepository.save(note)
    }

    override fun update(note: NoteDomain) {
        noteRepository.update(note)
    }

    override fun delete(id: Long) {
        noteRepository.delete(id)
    }

    override fun findAll(): List<NoteDomain> {
        return noteRepository.findAll()
    }

    override fun findById(id: Long): Optional<NoteDomain> {
        return noteRepository.findById(id)
    }

    override fun findByUser(user: String): List<NoteDomain> {
        return noteRepository.findByUser(user)
    }

    override fun findByTitle(title: String): List<NoteDomain> {
        return noteRepository.findByTitle(title)
    }
}