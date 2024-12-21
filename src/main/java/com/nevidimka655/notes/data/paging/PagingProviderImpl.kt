package com.nevidimka655.notes.data.paging

import androidx.paging.PagingData
import com.nevidimka655.notes.data.repository.RepositoryProviderImpl
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.paging.PagingProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

internal class PagingProviderImpl @Inject constructor(
    private val repositoryProviderImpl: RepositoryProviderImpl
): PagingProvider {

    @OptIn(ExperimentalCoroutinesApi::class)
    override operator fun invoke(): Flow<PagingData<Note>> {
        return repositoryProviderImpl.notesRepository.flatMapLatest { repo ->
            repo.listOrderDescAsc()
        }
    }

}