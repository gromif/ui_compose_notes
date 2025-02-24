package com.nevidimka655.notes.di

import androidx.paging.PagingData
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository
import com.nevidimka655.domain.notes.usecase.CreateUseCase
import com.nevidimka655.domain.notes.usecase.DeleteByIdUseCase
import com.nevidimka655.domain.notes.usecase.GetAeadPreferenceFlowUseCase
import com.nevidimka655.domain.notes.usecase.GetAeadPreferenceUseCase
import com.nevidimka655.domain.notes.usecase.GetNotesListFlow
import com.nevidimka655.domain.notes.usecase.LoadByIdUseCase
import com.nevidimka655.domain.notes.usecase.SetAeadPreferenceUseCase
import com.nevidimka655.domain.notes.usecase.UpdateByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideSetAeadPreferenceUseCase(
        settingsRepository: SettingsRepository,
    ): SetAeadPreferenceUseCase = SetAeadPreferenceUseCase(
        settingsRepository = settingsRepository,
    )

    @Provides
    fun provideGetAeadPreferenceFlowUseCase(
        settingsRepository: SettingsRepository,
    ): GetAeadPreferenceFlowUseCase = GetAeadPreferenceFlowUseCase(
        settingsRepository = settingsRepository,
    )

    @Provides
    fun provideGetAeadPreferenceUseCase(
        settingsRepository: SettingsRepository,
    ): GetAeadPreferenceUseCase = GetAeadPreferenceUseCase(
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