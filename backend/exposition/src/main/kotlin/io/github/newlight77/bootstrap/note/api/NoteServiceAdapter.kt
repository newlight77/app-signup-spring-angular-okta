package io.github.newlight77.bootstrap.note.api

import io.github.newlight77.bootstrap.note.model.NoteDomain
import io.github.newlight77.bootstrap.note.model.NoteModel
import io.github.newlight77.bootstrap.note.model.fromDomain
import io.github.newlight77.bootstrap.note.model.toDomain
import io.github.newlight77.bootstrap.note.service.INotesService
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteServiceAdapter(private var service: INotesService<NoteDomain, Long>) : INotesService<NoteModel, Long> {
    override fun save(note: NoteModel) {
        service.save(toDomain(note))
    }

    override fun update(note: NoteModel) {
        service.update(toDomain(note))
    }

    override fun delete(id: Long) {
        service.delete(id)
    }

    override fun findAll(): List<NoteModel> {
        return service.findAll().map { fromDomain(it) };
    }

    override fun findById(id: Long): Optional<NoteModel> {
        return service.findById(id).map { fromDomain(it) }
    }

    override fun findByUser(user: String): List<NoteModel> {
        return service.findByUser(user).map { fromDomain(it) }
    }

    override fun findByTitle(title: String): List<NoteModel> {
        return service.findByTitle(title).map { fromDomain(it) }
    }

}