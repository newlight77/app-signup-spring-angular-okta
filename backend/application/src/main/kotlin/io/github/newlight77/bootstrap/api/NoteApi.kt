package io.github.newlight77.bootstrap.api

import io.github.newlight77.bootstrap.model.NoteDomain
import io.github.newlight77.bootstrap.model.NoteModel
import io.github.newlight77.bootstrap.model.fromDomain
import io.github.newlight77.bootstrap.model.toDomain
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/notes")
class NoteApi(val repository: NotesRepository<NoteDomain, Long>) {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody note: NoteModel) {
        println("api create" + note)
        repository.save(toDomain(note))
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Long) {
        println("api delete" + id)
        repository.delete(id)
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody note: NoteModel) {
        println("api upodate" + note)
        val domain = toDomain(note)
        domain.id = id
        repository.update(toDomain(note))
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun byId(@PathVariable id: Long): NoteModel {
        println("api byId" + id)
        val note = repository.findById(id)
        return if (note.isPresent()) {
            fromDomain(note.get())
        } else
        {
            NoteModel()
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun byTitle(@RequestParam title: String) : List<NoteModel>{
        println("api byTitle" + title)
        return if (title.isEmpty()) {
            repository.findAll().map { fromDomain(it) }
        } else {
            repository.findByTitle(title).map { fromDomain(it) }
        }
    }

}