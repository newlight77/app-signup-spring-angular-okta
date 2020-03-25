package io.github.newlight77.bootstrap.note.repository

import io.github.newlight77.bootstrap.note.entity.NoteEntity
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler(NoteEntity::class)
class NoteEntityEventHandler {
    @HandleBeforeCreate
    fun handleCreate(note: NoteEntity) {
        println("event handling before creating note entity through the annotated @RepositoryRestResource : $note")
        note.user = "temporary"
    }
}
