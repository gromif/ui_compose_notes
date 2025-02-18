package com.nevidimka655.notes.di

import androidx.paging.PagingData
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.usecase.CreateUseCase
import com.nevidimka655.domain.notes.usecase.DeleteByIdUseCase
import com.nevidimka655.domain.notes.usecase.GetNotesListFlow
import com.nevidimka655.domain.notes.usecase.LoadByIdUseCase
import com.nevidimka655.domain.notes.usecase.UpdateByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object UseCases {

    @Provides
    fun provideGetPagingUseCase(
        pagingProvider: PagingProvider<PagingData<Note>>,
    ): GetNotesListFlow<PagingData<Note>> = GetNotesListFlow(
        pagingProvider = pagingProvider
    )

    @Provides
    fun provideCreateUseCase(
        repositoryImpl: Repository
    ): CreateUseCase = CreateUseCase(
        repository = repositoryImpl
    )

    @Provides
    fun provideLoadByIdUseCase(
        repositoryImpl: Repository
    ): LoadByIdUseCase = LoadByIdUseCase(
        repository = repositoryImpl
    )

    @Provides
    fun provideUpdateByIdUseCase(
        repositoryImpl: Repository
    ): UpdateByIdUseCase = UpdateByIdUseCase(
        repository = repositoryImpl
    )

    @Provides
    fun provideDeleteByIdUseCase(
        repositoryImpl: Repository
    ): DeleteByIdUseCase = DeleteByIdUseCase(
        repository = repositoryImpl
    )

}