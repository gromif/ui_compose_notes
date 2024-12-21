package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.database.NotesDao
import com.nevidimka655.notes.data.mappers.NoteItemEntityMapper
import com.nevidimka655.notes.data.repository.NotesRepositoryDecoratorImpl
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
        notesRepositoryDecoratorImpl: NotesRepositoryDecoratorImpl,
        //settingsDataStoreManager: SettingsDataStoreManager
    ): NotesRepositoryProvider = NotesRepositoryProvider(
        notesRepositoryDecorator = notesRepositoryDecoratorImpl,
        //aeadInfoFlow = settingsDataStoreManager.aeadInfoFlow
    )

    @Provides
    fun provideNotesRepositoryDecorator(
        notesRepositoryImpl: NotesRepositoryImpl,
        noteItemEntityMapper: NoteItemEntityMapper
    ): NotesRepositoryDecoratorImpl = NotesRepositoryDecoratorImpl(
        notesRepository = notesRepositoryImpl,
        noteItemEntityMapper = noteItemEntityMapper
    )

    @Provides
    fun provideNotesRepositoryImpl(notes: NotesDao): NotesRepositoryImpl =
        NotesRepositoryImpl(dao = notes)


    @Provides
    fun provideNotesItemEntityMapper(): NoteItemEntityMapper = NoteItemEntityMapper()

}