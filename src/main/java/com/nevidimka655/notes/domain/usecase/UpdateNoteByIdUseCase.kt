package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.NotesRepository

class UpdateNoteByIdUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Long, name: String, text: String) {
        repository.update(id = id, name = name, text = text)
    }

}