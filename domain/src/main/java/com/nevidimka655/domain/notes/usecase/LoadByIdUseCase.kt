package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository

class LoadByIdUseCase(
    private val repository: Repository,
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(id: Long): Note {
        val aead = settingsRepository.getAeadMode()
        return repository.getById(aead, id)
    }

}