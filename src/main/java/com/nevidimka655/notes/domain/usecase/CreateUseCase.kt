package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.Repository

class CreateUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(name: String, text: String) {
        repository.insert(
            name = name,
            text = text
        )
    }

}