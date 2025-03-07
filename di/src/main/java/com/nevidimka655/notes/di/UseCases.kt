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
        getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
        repositoryImpl: Repository,
    ): CreateUseCase = CreateUseCase(
        getAeadPreferenceUseCase = getAeadPreferenceUseCase,
        repository = repositoryImpl,
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
        getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
        repositoryImpl: Repository,
    ): LoadByIdUseCase = LoadByIdUseCase(
        getAeadPreferenceUseCase = getAeadPreferenceUseCase,
        repository = repositoryImpl,
    )

    @Provides
    fun provideUpdateByIdUseCase(
        getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
        repositoryImpl: Repository,
    ): UpdateByIdUseCase = UpdateByIdUseCase(
        getAeadPreferenceUseCase = getAeadPreferenceUseCase,
        repository = repositoryImpl,
    )

    @Provides
    fun provideDeleteByIdUseCase(
        repositoryImpl: Repository
    ): DeleteByIdUseCase = DeleteByIdUseCase(
        repository = repositoryImpl
    )

}