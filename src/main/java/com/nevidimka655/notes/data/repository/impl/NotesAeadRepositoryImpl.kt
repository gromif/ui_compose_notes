package com.nevidimka655.notes.data.repository.impl

import androidx.paging.PagingSource
import com.nevidimka655.notes.data.database.NoteItemEntity
import com.nevidimka655.notes.data.database.NotesDao
import com.nevidimka655.notes.data.database.tuples.TransformNotesTuple
import com.nevidimka655.notes.data.mappers.NoteItemEntityMapper
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.repository.NotesRepository

class NotesAeadRepositoryImpl(
    private val dao: NotesDao,
    private val noteItemEntityMapper: NoteItemEntityMapper
): NotesRepository {
    override suspend fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: Long, name: String, text: String) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(name: String, text: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Note {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransform(transformNotesTuple: TransformNotesTuple) {
        TODO("Not yet implemented")
    }

    override suspend fun getTransformItems(
        pageSize: Int,
        pageIndex: Int
    ): List<TransformNotesTuple> {
        TODO("Not yet implemented")
    }

    override fun listOrderDescAsc(): PagingSource<Int, NoteItemEntity> {
        TODO("Not yet implemented")
    }
}