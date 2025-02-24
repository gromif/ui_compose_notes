package com.nevidimka655.notes.di

import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.usecase.GetAeadPreferenceUseCase
import com.nevidimka655.domain.notes.usecase.SetAeadPreferenceUseCase
import com.nevidimka655.domain.notes.usecase.UpdateNotesAeadUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object WorkerUseCases {

    @Provides
    fun provideUpdateNotesAeadUseCase(
        getAeadPreferenceUseCase: GetAeadPreferenceUseCase,
        setAeadPreferenceUseCase: SetAeadPreferenceUseCase,
        repositoryImpl: Repository
    ): UpdateNotesAeadUseCase = UpdateNotesAeadUseCase(
        getAeadPreferenceUseCase = getAeadPreferenceUseCase,
        setAeadPreferenceUseCase = setAeadPreferenceUseCase,
        repository = repositoryImpl
    )

}