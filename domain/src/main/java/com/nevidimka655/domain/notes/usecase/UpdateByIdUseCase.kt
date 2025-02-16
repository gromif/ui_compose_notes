package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository

class UpdateByIdUseCase(
    private val repository: Repository,
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(id: Long, name: String, text: String) {
        val aead = settingsRepository.getAeadTemplateIndex()
        repository.update(
            aead = aead,
            id = id,
            name = name,
            text = text
        )
    }

}