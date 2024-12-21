package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.Repository

class UpdateByIdUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(id: Long, name: String, text: String) {
        repository.update(id = id, name = name, text = text)
    }

}