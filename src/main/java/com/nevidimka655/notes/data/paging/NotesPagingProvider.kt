package com.nevidimka655.notes.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.nevidimka655.notes.data.mappers.NoteItemEntityMapper
import com.nevidimka655.notes.data.repository.NotesRepositoryProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesPagingProvider @Inject constructor(
    private val notesRepositoryProvider: NotesRepositoryProvider,
    private val noteItemEntityMapper: NoteItemEntityMapper
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun createPagingSource() = notesRepositoryProvider.notesRepository.flatMapLatest { repo ->
        Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                repo.listOrderDescAsc()
            }
        ).flow.map { pagingData ->
            pagingData.map { noteItemEntityMapper(item = it) }
        }
    }

}