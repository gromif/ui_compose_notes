package com.nevidimka655.notes.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface RepositoryProvider {

    val notesRepository: Flow<Repository>

}