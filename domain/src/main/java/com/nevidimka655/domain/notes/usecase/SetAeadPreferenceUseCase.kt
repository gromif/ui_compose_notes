package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.repository.SettingsRepository

class SetAeadPreferenceUseCase(
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(targetAead: AeadMode) {
        settingsRepository.setAeadMode(targetAead)
    }

}