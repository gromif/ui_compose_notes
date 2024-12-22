package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.paging.PagingProviderImpl
import com.nevidimka655.notes.domain.repository.Repository
import com.nevidimka655.domain.notes.usecase.CreateUseCase
import com.nevidimka655.domain.notes.usecase.DeleteByIdUseCase
import com.nevidimka655.domain.notes.usecase.GetPagingUseCase
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
        pagingProviderImpl: PagingProviderImpl
    ): GetPagingUseCase = GetPagingUseCase(
        pagingProvider = pagingProviderImpl
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