package com.nevidimka655.domain.notes.usecase

import com.nevidimka655.domain.notes.paging.PagingProvider
import kotlinx.coroutines.flow.Flow

class GetNotesListFlow<out T>(
    private val pagingProvider: PagingProvider<T>
) {

    operator fun invoke(): Flow<T> {
        return pagingProvider()
    }

}