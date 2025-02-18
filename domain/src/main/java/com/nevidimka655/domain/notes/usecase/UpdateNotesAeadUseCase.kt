package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.repository.Repository

class UpdateNotesAeadUseCase(
    private val repository: Repository,
) {

    suspend operator fun invoke(targetAeadMode: AeadMode) {
        repository.changeAead(targetAeadMode)
    }

}