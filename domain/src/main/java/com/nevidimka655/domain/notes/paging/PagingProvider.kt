package com.nevidimka655.domain.notes.paging

import kotlinx.coroutines.flow.Flow

interface PagingProvider<out T> {

    operator fun invoke(): Flow<T>

}