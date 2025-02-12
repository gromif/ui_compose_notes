package com.nevidimka655.notes.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.NotesDao
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.paging.PagingProvider
import io.gromif.astracrypt.utils.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PagingProviderImpl(
    private val dao: NotesDao,
    private val noteMapper: Mapper<NoteItemEntity, Note>,
): PagingProvider<PagingData<Note>> {

    @OptIn(ExperimentalCoroutinesApi::class)
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
            pagingData.map { noteMapper(item = it) }
        }
    }

}