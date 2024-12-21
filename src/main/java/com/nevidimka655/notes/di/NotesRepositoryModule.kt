package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.database.NotesDao
import com.nevidimka655.notes.data.mappers.NoteItemEntityMapper
import com.nevidimka655.notes.data.repository.NotesRepositoryProvider
import com.nevidimka655.notes.data.repository.impl.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NotesRepositoryModule {

    @Provides
    fun provideNotesRepositoryProvider(
        notesRepositoryImpl: NotesRepositoryImpl,
        //settingsDataStoreManager: SettingsDataStoreManager
    ): NotesRepositoryProvider = NotesRepositoryProvider(
        repository = notesRepositoryImpl,
        //aeadInfoFlow = settingsDataStoreManager.aeadInfoFlow
    )

    @Provides
    fun provideNotesRepositoryImpl(
        notes: NotesDao,
        noteItemEntityMapper: NoteItemEntityMapper
    ): NotesRepositoryImpl = NotesRepositoryImpl(
        dao = notes,
        noteItemEntityMapper = noteItemEntityMapper
    )


    @Provides
    fun provideNotesItemEntityMapper(): NoteItemEntityMapper = NoteItemEntityMapper()

}