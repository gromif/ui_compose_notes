package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.paging.PagingProviderImpl
import com.nevidimka655.notes.data.repository.RepositoryImpl
import com.nevidimka655.notes.domain.usecase.CreateUseCase
import com.nevidimka655.notes.domain.usecase.DeleteByIdUseCase
import com.nevidimka655.notes.domain.usecase.GetPagingUseCase
import com.nevidimka655.notes.domain.usecase.LoadByIdUseCase
import com.nevidimka655.notes.domain.usecase.UpdateByIdUseCase
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
    fun provideCreateUseCase(
        repositoryImpl: RepositoryImpl
    ): CreateUseCase = CreateUseCase(
        repository = repositoryImpl
    )

    @Provides
    fun provideLoadByIdUseCase(
        repositoryImpl: RepositoryImpl
    ): LoadByIdUseCase = LoadByIdUseCase(
        repository = repositoryImpl
    )

    @Provides
    fun provideUpdateByIdUseCase(
        repositoryImpl: RepositoryImpl
    ): UpdateByIdUseCase = UpdateByIdUseCase(
        repository = repositoryImpl
    )

    @Provides
    fun provideDeleteByIdUseCase(
        repositoryImpl: RepositoryImpl
    ): DeleteByIdUseCase = DeleteByIdUseCase(
        repository = repositoryImpl
    )

}