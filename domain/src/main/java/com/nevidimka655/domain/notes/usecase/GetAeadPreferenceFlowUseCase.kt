package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetAeadPreferenceFlowUseCase(
    private val settingsRepository: SettingsRepository,
) {

    operator fun invoke(): Flow<AeadMode> {
        return settingsRepository.getAeadModeFlow()
    }

}