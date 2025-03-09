package io.gromif.notes.domain.model

data class Note(
    val id: Long,
    val name: String,
    val text: String,
    val textPreview: String,
    val state: NoteState,
    val creationTime: String
)