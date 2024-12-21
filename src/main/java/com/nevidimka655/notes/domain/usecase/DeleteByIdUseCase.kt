package com.nevidimka655.notes.domain.usecase

import com.nevidimka655.notes.domain.repository.Repository

class DeleteByIdUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(id: Long) {
        repository.deleteById(id = id)
    }

}