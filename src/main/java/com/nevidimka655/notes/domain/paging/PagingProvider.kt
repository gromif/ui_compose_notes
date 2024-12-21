package com.nevidimka655.notes.domain.paging

import androidx.paging.PagingData
import com.nevidimka655.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface PagingProvider {

    operator fun invoke(): Flow<PagingData<Note>>

}