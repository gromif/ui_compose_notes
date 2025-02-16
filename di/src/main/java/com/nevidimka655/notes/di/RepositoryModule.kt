package com.nevidimka655.notes.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.PagingData
import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.NotesDao
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.domain.notes.repository.SettingsRepository
import com.nevidimka655.notes.data.mappers.NoteMapper
import com.nevidimka655.notes.data.paging.PagingProviderImpl
import com.nevidimka655.notes.data.repository.RepositoryImpl
import com.nevidimka655.notes.data.repository.SettingsRepositoryImpl
import com.nevidimka655.notes.data.util.AeadHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.gromif.astracrypt.utils.Mapper

@Module
@InstallIn(ViewModelComponent::class)
internal object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(
        dao: NotesDao,
        aeadHandler: AeadHandler,
        dataToDomainMapper: Mapper<NoteItemEntity, Note>
    ): Repository = RepositoryImpl(
        dao = dao,
        aeadHandler = aeadHandler,
        noteMapper = dataToDomainMapper
    )

    @ViewModelScoped
    @Provides
    fun provideSettingsRepository(
        @NotesDataStore dataStore: DataStore<Preferences>
    ): SettingsRepository = SettingsRepositoryImpl(dataStore = dataStore)

    @ViewModelScoped
    @Provides
    fun providePagingProvider(
        dao: NotesDao,
        settingsRepository: SettingsRepository,
        aeadHandler: AeadHandler,
        dataToDomainMapper: Mapper<NoteItemEntity, Note>
    ): PagingProvider<PagingData<Note>> = PagingProviderImpl(
        dao = dao,
        settingsRepository = settingsRepository,
        aeadHandler = aeadHandler,
        noteMapper = dataToDomainMapper
    )

    @ViewModelScoped
    @Provides
    fun provideNoteMapper(): Mapper<NoteItemEntity, Note> = NoteMapper()

}