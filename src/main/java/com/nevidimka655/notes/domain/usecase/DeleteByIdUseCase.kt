package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.NotesRepositoryDecorator

class DeleteByIdUseCase(
    private val repository: NotesRepositoryDecorator
) {

    suspend operator fun invoke(id: Long) {
        repository.deleteById(id = id)
    }

}