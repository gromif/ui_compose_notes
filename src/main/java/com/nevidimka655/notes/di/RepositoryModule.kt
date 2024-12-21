package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.database.NotesDao
import com.nevidimka655.notes.data.mappers.DataToDomainMapper
import com.nevidimka655.notes.data.repository.RepositoryImpl
import com.nevidimka655.notes.data.repository.RepositoryProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object RepositoryModule {

    @Provides
    fun provideRepositoryProvider(
        repositoryImpl: RepositoryImpl,
        //settingsDataStoreManager: SettingsDataStoreManager
    ): RepositoryProviderImpl = RepositoryProviderImpl(
        repository = repositoryImpl,
        //aeadInfoFlow = settingsDataStoreManager.aeadInfoFlow
    )

    @Provides
    fun provideRepositoryImpl(
        dao: NotesDao,
        dataToDomainMapper: DataToDomainMapper
    ): RepositoryImpl = RepositoryImpl(
        dao = dao,
        dataToDomainMapper = dataToDomainMapper
    )


    @Provides
    fun provideDataToDomainMapper(): DataToDomainMapper = DataToDomainMapper()

}