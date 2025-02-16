package com.nevidimka655.notes.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.NotesDao
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import com.nevidimka655.domain.notes.repository.SettingsRepository
import com.nevidimka655.notes.data.util.AeadHandler
import io.gromif.astracrypt.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PagingProviderImpl(
    private val dao: NotesDao,
    private val settingsRepository: SettingsRepository,
    private val aeadHandler: AeadHandler,
    private val noteMapper: Mapper<NoteItemEntity, Note>,
): PagingProvider<PagingData<Note>> {

    override operator fun invoke(): Flow<PagingData<Note>> {
        return Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                dao.listOrderDescAsc()
            }
        ).flow.map { pagingData ->
            val aead = settingsRepository.getAeadTemplateIndex()
            if (aead > -1) pagingData.map {
                val currentItem = aeadHandler.decryptNoteEntity(aead, it)
                noteMapper(item = currentItem)
            } else pagingData.map { noteMapper(item = it) }
        }
    }

}