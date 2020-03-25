package io.github.newlight77.bootstrap.note.config

import io.github.newlight77.bootstrap.note.model.NoteDomain
import io.github.newlight77.bootstrap.note.port.INotesRepository
import io.github.newlight77.bootstrap.note.service.INotesService
import io.github.newlight77.bootstrap.note.service.NoteService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NoteConfiguration {
    @Bean
    fun noteService(repository: INotesRepository<NoteDomain, Long>): INotesService<NoteDomain, Long> {
        return NoteService(repository)
    }
}