package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.repository.Repository

class LoadByIdUseCase(
    private val getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
    private val repository: Repository,
) {

    suspend operator fun invoke(id: Long): Note {
        val aead = getAeadPreferenceUseCase()
        return repository.getById(aead, id)
    }

}