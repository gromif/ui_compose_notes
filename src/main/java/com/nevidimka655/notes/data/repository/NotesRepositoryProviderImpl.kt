package com.nevidimka655.notes.data.repository

import com.nevidimka655.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NotesRepositoryProviderImpl(
    private val repository: NotesRepository,
    //aeadInfoFlow: Flow<AeadInfo> TODO(Core)
) {

    val notesRepository: Flow<NotesRepository> = MutableStateFlow(repository) /*aeadInfoFlow.map {
        if (it.database != null) notesRepositoryImpl else notesRepositoryImpl
    }.distinctUntilChanged()*/ // TODO(Core)

}