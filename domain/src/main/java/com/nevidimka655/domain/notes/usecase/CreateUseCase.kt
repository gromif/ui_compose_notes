package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.repository.Repository

class CreateUseCase(
    private val getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
    private val repository: Repository,
) {

    suspend operator fun invoke(name: String, text: String) {
        val aead = getAeadPreferenceUseCase()
        repository.insert(aead, name, text)
    }

}