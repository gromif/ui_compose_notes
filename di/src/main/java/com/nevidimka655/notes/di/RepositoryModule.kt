package com.nevidimka655.notes.di

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
import com.nevidimka655.notes.data.util.AeadHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gromif.astracrypt.utils.Mapper

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

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

    @Provides
    fun provideNoteMapper(): Mapper<NoteItemEntity, Note> = NoteMapper()

}