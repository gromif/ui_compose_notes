package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.repository.NotesRepository

class LoadNoteByIdUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Long): Note {
        return repository.getById(id = id)
    }

}