package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository

class CreateUseCase(
    private val repository: Repository,
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(name: String, text: String) {
        val aead = settingsRepository.getAeadMode()
        repository.insert(aead, name, text)
    }

}