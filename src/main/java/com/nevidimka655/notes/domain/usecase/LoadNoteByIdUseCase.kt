package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.repository.NotesRepositoryDecorator

class LoadNoteByIdUseCase(
    private val repository: NotesRepositoryDecorator
) {

    suspend operator fun invoke(id: Long): Note {
        return repository.getById(id = id)
    }

}