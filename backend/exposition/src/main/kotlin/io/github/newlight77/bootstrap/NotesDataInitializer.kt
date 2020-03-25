package io.github.newlight77.bootstrap

import io.github.newlight77.bootstrap.note.model.NoteModel
import io.github.newlight77.bootstrap.note.model.fromDomain
import io.github.newlight77.bootstrap.note.model.toDomain
import io.github.newlight77.bootstrap.note.service.INotesService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(val service: INotesService<NoteModel, Long>) : ApplicationRunner {
    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        modelData().map { toDomain(it) }
              .forEach() {
                  service.save(fromDomain(it))
        }
        service.findAll().forEach { println(it) }
    }
}

fun modelData(): List<NoteModel> {
    return listOf(
            NoteModel(title = "Note 1", text = "empty", author = "user"),
            NoteModel(title = "Note 2", text = "empty", author = "user"),
            NoteModel(title = "Note 3", text = "empty", author = "user")
    )
}
