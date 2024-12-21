package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.paging.PagingProviderImpl
import com.nevidimka655.notes.data.repository.impl.NotesRepositoryImpl
import com.nevidimka655.notes.domain.usecase.CreateNewNoteUseCase
import com.nevidimka655.notes.domain.usecase.DeleteByIdUseCase
import com.nevidimka655.notes.domain.usecase.GetPagingUseCase
import com.nevidimka655.notes.domain.usecase.LoadNoteByIdUseCase
import com.nevidimka655.notes.domain.usecase.UpdateNoteByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCases {

    @Provides
    fun provideGetPagingUseCase(
        pagingProviderImpl: PagingProviderImpl
    ): GetPagingUseCase = GetPagingUseCase(
        pagingProvider = pagingProviderImpl
    )

    @Provides
    fun provideCreateNewNoteUseCase(
        notesRepositoryImpl: NotesRepositoryImpl
    ): CreateNewNoteUseCase = CreateNewNoteUseCase(
        repository = notesRepositoryImpl
    )

    @Provides
    fun provideLoadNoteByIdUseCase(
        notesRepositoryImpl: NotesRepositoryImpl
    ): LoadNoteByIdUseCase = LoadNoteByIdUseCase(
        repository = notesRepositoryImpl
    )

    @Provides
    fun provideUpdateNoteByIdUseCase(
        notesRepositoryImpl: NotesRepositoryImpl
    ): UpdateNoteByIdUseCase = UpdateNoteByIdUseCase(
        repository = notesRepositoryImpl
    )

    @Provides
    fun provideDeleteByIdUseCase(
        notesRepositoryImpl: NotesRepositoryImpl
    ): DeleteByIdUseCase = DeleteByIdUseCase(
        repository = notesRepositoryImpl
    )

}