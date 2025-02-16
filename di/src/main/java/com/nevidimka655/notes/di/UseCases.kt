package com.nevidimka655.notes.di

import androidx.paging.PagingData
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository
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
        pagingProvider = pagingProvider,
    )

    @Provides
    fun provideCreateUseCase(
        repositoryImpl: Repository,
        settingsRepository: SettingsRepository,
    ): CreateUseCase = CreateUseCase(
        repository = repositoryImpl,
        settingsRepository = settingsRepository,
    )

    @Provides
    fun provideLoadByIdUseCase(
        repositoryImpl: Repository,
        settingsRepository: SettingsRepository,
    ): LoadByIdUseCase = LoadByIdUseCase(
        repository = repositoryImpl,
        settingsRepository = settingsRepository,
    )

    @Provides
    fun provideUpdateByIdUseCase(
        repositoryImpl: Repository,
        settingsRepository: SettingsRepository,
    ): UpdateByIdUseCase = UpdateByIdUseCase(
        repository = repositoryImpl,
        settingsRepository = settingsRepository,
    )

    @Provides
    fun provideDeleteByIdUseCase(
        repositoryImpl: Repository
    ): DeleteByIdUseCase = DeleteByIdUseCase(
        repository = repositoryImpl
    )

}