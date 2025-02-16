package com.nevidimka655.domain.notes.repository

import com.nevidimka655.domain.notes.model.Note

interface Repository {

    suspend fun deleteById(id: Long)

    suspend fun update(
        aead: Int,
        id: Long,
        name: String,
        text: String,
    )

    suspend fun insert(
        aead: Int,
        name: String,
        text: String,
    )

    suspend fun getById(aead: Int, id: Long): Note

    suspend fun getByPage(pageSize: Int, pageIndex: Int): List<Note>

}