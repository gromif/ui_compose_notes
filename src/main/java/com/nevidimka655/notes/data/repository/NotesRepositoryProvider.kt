package com.nevidimka655.notes.data.repository

import com.nevidimka655.notes.domain.repository.NotesRepositoryDecorator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NotesRepositoryProvider(
    private val notesRepositoryDecorator: NotesRepositoryDecorator,
    //aeadInfoFlow: Flow<AeadInfo> TODO(Core)
) {

    val notesRepository: Flow<NotesRepositoryDecorator> = MutableStateFlow(notesRepositoryDecorator) /*aeadInfoFlow.map {
        if (it.database != null) notesRepositoryImpl else notesRepositoryImpl
    }.distinctUntilChanged()*/ // TODO(Core)

}