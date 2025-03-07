package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.repository.Repository

class UpdateByIdUseCase(
    private val getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
    private val repository: Repository,
) {

    suspend operator fun invoke(id: Long, name: String, text: String) {
        val aead = getAeadPreferenceUseCase()
        repository.update(
            aead = aead,
            id = id,
            name = name,
            text = text
        )
    }

}