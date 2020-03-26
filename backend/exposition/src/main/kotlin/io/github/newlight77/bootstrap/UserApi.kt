package io.github.newlight77.bootstrap

import io.github.newlight77.bootstrap.note.model.NoteModel
import io.github.newlight77.bootstrap.note.service.INotesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("api/users")
class UserApi(val service: INotesService<NoteModel, Long>) {

    @GetMapping("/notes")
    fun notes(principal: Principal): List<NoteModel> {
        println("Fetching notes for user: ${principal.name}")
        val notes = service.findByUser(principal.name)
        return if (notes.isEmpty()) {
            listOf()
        } else {
            notes
        }
    }
}