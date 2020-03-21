package io.github.newlight77.bootstrap.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class NoteModel(var id: Long? = null,
                     var title: String? = null,
                     var text: String? = null,
                     @JsonIgnore var author: String? = null)

fun fromDomain(domain: NoteDomain): NoteModel {
    return NoteModel().copy(
            domain.id,
            domain.title,
            domain.text,
            domain.author
    );
}

fun toDomain(model: NoteModel): NoteDomain {
    return NoteDomain().copy(
            model.id,
            model.title,
            model.text,
            model.author
    );
}