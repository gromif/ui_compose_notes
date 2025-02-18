package com.nevidimka655.domain.notes.repository

import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.model.Note

interface Repository {

    suspend fun deleteById(id: Long)

    suspend fun update(
        aead: AeadMode,
        id: Long,
        name: String,
        text: String,
    )

    suspend fun insert(
        aead: AeadMode,
        name: String,
        text: String,
    )

    suspend fun getById(aead: AeadMode, id: Long): Note

    suspend fun changeAead(targetAeadMode: AeadMode)

}