package com.nevidimka655.notes.domain.usecase

import androidx.paging.PagingData
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.paging.PagingProvider
import kotlinx.coroutines.flow.Flow

class GetPagingUseCase(
    private val pagingProvider: PagingProvider
) {

    operator fun invoke(): Flow<PagingData<Note>> {
        return pagingProvider()
    }

}