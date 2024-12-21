package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.NotesRepository

class DeleteByIdUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(id: Long) {
        repository.deleteById(id = id)
    }

}