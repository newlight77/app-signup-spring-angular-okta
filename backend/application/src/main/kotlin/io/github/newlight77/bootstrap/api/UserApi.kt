package io.github.newlight77.bootstrap.api

import io.github.newlight77.bootstrap.model.NoteDomain
import io.github.newlight77.bootstrap.model.NoteModel
import io.github.newlight77.bootstrap.model.fromDomain
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("api/users")
class UserApi(val repository: NotesRepository<NoteDomain, Long>) {

    @GetMapping("/notes")
    fun notes(principal: Principal): List<NoteModel> {
        println("Fetching notes for user: ${principal.name}")
        val notes = repository.findByUser(principal.name)
        return if (notes.isEmpty()) {
            listOf()
        } else {
            notes.map { fromDomain(it) }
        }
    }
}