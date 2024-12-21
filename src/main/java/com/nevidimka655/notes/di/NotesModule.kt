package com.nevidimka655.notes.di

import com.nevidimka655.notes.domain.usecase.CreateNewNoteUseCase
import com.nevidimka655.notes.domain.usecase.DeleteByIdUseCase
import com.nevidimka655.notes.domain.usecase.LoadNoteByIdUseCase
import com.nevidimka655.notes.domain.usecase.UpdateNoteByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NotesModule {

    @Provides
    fun provideCreateNewNoteUseCase(
        notesRepositoryDecorator: NotesRepositoryDecoratorImpl
    ): CreateNewNoteUseCase = CreateNewNoteUseCase(
        repository = notesRepositoryDecorator
    )

    @Provides
    fun provideLoadNoteByIdUseCase(
        notesRepositoryDecorator: NotesRepositoryDecoratorImpl
    ): LoadNoteByIdUseCase = LoadNoteByIdUseCase(
        repository = notesRepositoryDecorator
    )

    @Provides
    fun provideUpdateNoteByIdUseCase(
        notesRepositoryDecorator: NotesRepositoryDecoratorImpl
    ): UpdateNoteByIdUseCase = UpdateNoteByIdUseCase(
        repository = notesRepositoryDecorator
    )

    @Provides
    fun provideDeleteByIdUseCase(
        notesRepositoryDecorator: NotesRepositoryDecoratorImpl
    ): DeleteByIdUseCase = DeleteByIdUseCase(
        repository = notesRepositoryDecorator
    )

}