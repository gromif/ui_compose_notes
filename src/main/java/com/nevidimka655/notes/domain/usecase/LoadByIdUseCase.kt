package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.repository.Repository

class LoadByIdUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(id: Long): Note {
        return repository.getById(id = id)
    }

}