package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.repository.SettingsRepository

class GetAeadPreferenceUseCase(
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(): AeadMode {
        return settingsRepository.getAeadMode()
    }

}