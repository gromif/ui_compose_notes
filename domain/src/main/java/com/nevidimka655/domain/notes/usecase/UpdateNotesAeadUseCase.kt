package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.repository.Repository

class UpdateNotesAeadUseCase(
    private val getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
    private val setAeadPreferenceUseCase: SetAeadPreferenceUseCase,
    private val repository: Repository,
) {

    suspend operator fun invoke(targetAeadMode: AeadMode) {
        val sourceAeadMode = getAeadPreferenceUseCase()
        repository.changeAead(
            sourceAeadMode = sourceAeadMode,
            targetAeadMode = targetAeadMode
        )
        setAeadPreferenceUseCase(targetAeadMode)
    }

}