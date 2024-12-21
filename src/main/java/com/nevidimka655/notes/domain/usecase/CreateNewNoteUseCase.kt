package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.NotesRepository

class CreateNewNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(name: String, text: String) {
        repository.insert(
            name = name,
            text = text
        )
    }

}